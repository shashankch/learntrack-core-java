package com.edtech.learntrack.service;

import com.edtech.learntrack.entity.Course;
import com.edtech.learntrack.entity.Enrollment;
import com.edtech.learntrack.entity.Student;
import com.edtech.learntrack.enums.EnrollmentStatus;
import com.edtech.learntrack.exception.EntityNotFoundException;
import com.edtech.learntrack.exception.InvalidInputException;
import com.edtech.learntrack.repository.EnrollmentRepository;
import com.edtech.learntrack.util.IdGenerator;
import com.edtech.learntrack.util.InputValidator;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentService studentService,
                             CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(long studentId, int courseId, String enrollmentDate) {
        InputValidator.validateInput((int) studentId, "Student ID");
        InputValidator.validateInput(courseId, "Course ID");
        InputValidator.validateInput(enrollmentDate, "Enrollment date");

        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(enrollmentDate);
        } catch (DateTimeParseException ex) {
            throw new InvalidInputException("Invalid date format. Use yyyy-MM-dd (example: 2026-02-06).");
        }

        Student student = studentService.findStudentById(studentId);
        if (!student.getStatus().isActive()) {
            throw new InvalidInputException("Student is deactivated. Cannot enroll.");
        }

        Course course = courseService.findCourseById(courseId);
        if (!course.getStatus().isActive()) {
            throw new InvalidInputException("Course is inactive. Cannot enroll.");
        }

        //check if student is already enrolled in the course to prevent duplicate enrollments
        List<Enrollment> existingEnrollments = enrollmentRepository.findByStudentId(studentId);
        for (Enrollment enrollment : existingEnrollments) {
            if (enrollment.getCourseId() == courseId) {
                throw new InvalidInputException("Student is already enrolled in this course.");
            }
        }

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, parsedDate, EnrollmentStatus.ACTIVE);
        enrollmentRepository.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> listEnrollmentsForStudent(long studentId) {
        try {
            studentService.findStudentById(studentId);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Cannot list enrollments. Student with ID " + studentId + " does not exist.");
        }
        return enrollmentRepository.findByStudentId(studentId);
    }

    public Enrollment findEnrollmentById(int id) {
        Enrollment enrollment = enrollmentRepository.findById(id);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment not found for id: " + id);
        }
        return enrollment;
    }

    public void updateEnrollmentStatus(int enrollmentId, EnrollmentStatus status) {
        if (status == null) {
            throw new InvalidInputException("Status cannot be null.");
        }
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(status);
    }
}
