package com.edtech.learntrack.repository;

import com.edtech.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public void add(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Optional<Student> findById(long id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }
}
