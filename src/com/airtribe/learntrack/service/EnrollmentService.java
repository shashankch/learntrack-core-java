package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
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

    public Enrollment enrollStudent(int studentId, int courseId, String enrollmentDate) {
        InputValidator.validateInput(studentId, "Student ID");
        InputValidator.validateInput(courseId, "Course ID");
        InputValidator.validateInput(enrollmentDate, "Enrollment date");

        Student s = studentService.findStudentById(studentId);
        if (!s.getStatus().isActive()) {
            throw new InvalidInputException("Student is deactivated. Cannot enroll.");
        }

        Course c = courseService.findCourseById(courseId);
        if (!c.getStatus().isActive()) {
            throw new InvalidInputException("Course is inactive. Cannot enroll.");
        }

        //check if student is already enrolled in the course to prevent duplicate enrollments
        List<Enrollment> existingEnrollments = enrollmentRepository.findByStudentId(studentId);
        for (Enrollment e : existingEnrollments) {
            if (e.getCourseId() == courseId) {
                throw new InvalidInputException("Student is already enrolled in this course.");
            }
        }

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment e = new Enrollment(id, studentId, courseId, enrollmentDate, EnrollmentStatus.ACTIVE);
        enrollmentRepository.add(e);
        return e;
    }

    public List<Enrollment> listEnrollmentsForStudent(int studentId) {
        // validate student exists
        studentService.findStudentById(studentId);
        return enrollmentRepository.findByStudentId(studentId);
    }

    public Enrollment findEnrollmentById(int id) {
        Enrollment e = enrollmentRepository.findById(id);
        if (e == null) {
            throw new EntityNotFoundException("Enrollment not found for id: " + id);
        }
        return e;
    }

    public void updateEnrollmentStatus(int enrollmentId, EnrollmentStatus status) {
        if (status == null) {
            throw new InvalidInputException("Status cannot be null.");
        }
        Enrollment e = findEnrollmentById(enrollmentId);
        e.setStatus(status);
    }
}
