package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.StudentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
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

        int id = IdGenerator.getNextStudentId();
        Student s = new Student(id, firstName, lastName, email, batch, StudentStatus.ACTIVE);
        studentRepository.add(s);
        return s;
    }

    // Overloading example: without email
    public Student addStudent(String firstName, String lastName, String batch) {
        InputValidator.validateInput(firstName, "First name");
        InputValidator.validateInput(lastName, "Last name");
        InputValidator.validateInput(batch, "Batch");

        int id = IdGenerator.getNextStudentId();
        Student s = new Student(id, firstName, lastName, batch);
        studentRepository.add(s);
        return s;
    }

    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(int id) {
        Student s = studentRepository.findById(id);
        if (s == null) {
            throw new EntityNotFoundException("Student not found for id: " + id);
        }
        return s;
    }

    public void updateStudentEmail(int id, String newEmail) {
        InputValidator.requireEmailLike(newEmail);
        Student s = findStudentById(id);
        s.setEmail(newEmail);
    }

    public void deactivateStudent(int id) {
        Student s = findStudentById(id);
        s.setStatus(StudentStatus.INACTIVE);
    }
}
