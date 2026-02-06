package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.List;

public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String name, String description, int durationInWeeks) {
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
        Course c = courseRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Course not found for id: " + id);
        }
        return c;
    }

    public void setCourseActive(int id, CourseStatus active) {
        Course c = findCourseById(id);
        c.setStatus(active);
    }
}
