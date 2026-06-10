package com.edtech.learntrack.service;

import com.edtech.learntrack.entity.Student;
import com.edtech.learntrack.enums.StudentStatus;
import com.edtech.learntrack.exception.EntityNotFoundException;
import com.edtech.learntrack.repository.StudentRepository;
import com.edtech.learntrack.util.IdGenerator;
import com.edtech.learntrack.util.InputValidator;
import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    //overloading usage
    public Student addStudent(String firstName, String lastName, String email, String batch) {
        InputValidator.validateInput(firstName, "First name");
        InputValidator.validateInput(lastName, "Last name");
        InputValidator.validateInput(batch, "Batch");
        InputValidator.requireEmailLike(email);

        long id = IdGenerator.getNextStudentId();
        Student s = new Student(id, firstName, lastName, email, batch, StudentStatus.ACTIVE);
        studentRepository.add(s);
        return s;
    }

    // Overloading example: without email
    public Student addStudent(String firstName, String lastName, String batch) {
        InputValidator.validateInput(firstName, "First name");
        InputValidator.validateInput(lastName, "Last name");
        InputValidator.validateInput(batch, "Batch");

        long id = IdGenerator.getNextStudentId();
        Student s = new Student(id, firstName, lastName, batch);
        studentRepository.add(s);
        return s;
    }

    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found for id: " + id));
    }

    public void updateStudentEmail(long id, String newEmail) {
        InputValidator.requireEmailLike(newEmail);
        Student s = findStudentById(id);
        s.setEmail(newEmail);
    }

    public void deactivateStudent(long id) {
        Student s = findStudentById(id);
        s.setStatus(StudentStatus.INACTIVE);
    }
}
