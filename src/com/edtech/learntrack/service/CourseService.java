package com.edtech.learntrack.service;

import com.edtech.learntrack.entity.Course;
import com.edtech.learntrack.enums.CourseStatus;
import com.edtech.learntrack.exception.EntityNotFoundException;
import com.edtech.learntrack.exception.InvalidInputException;
import com.edtech.learntrack.repository.CourseRepository;
import com.edtech.learntrack.util.IdGenerator;
import com.edtech.learntrack.util.InputValidator;
import java.util.List;

public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String name, String description, int durationInWeeks) {
        if (durationInWeeks <= 0) {
            throw new InvalidInputException("Duration (weeks) must be a positive number.");
        }
        InputValidator.validateInput(name, "Course name");
        InputValidator.validateInput(durationInWeeks, "Duration (weeks)");

        int id = IdGenerator.getNextCourseId();
        Course c = new Course(id, name, description, durationInWeeks, CourseStatus.ACTIVE);
        courseRepository.add(c);
        return c;
    }

    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found for id: " + id));
    }

    public void setCourseStatus(int id, CourseStatus status) {
        Course c = findCourseById(id);
        c.setStatus(status);
    }
}
