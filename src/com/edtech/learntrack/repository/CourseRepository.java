package com.edtech.learntrack.repository;

import com.edtech.learntrack.entity.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public void add(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        courses.add(course);
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
}
