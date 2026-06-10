package com.edtech.learntrack.repository;

import com.edtech.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public void add(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    public Enrollment findById(int id) {
        for (Enrollment e : enrollments) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public List<Enrollment> findByStudentId(long studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }
}
