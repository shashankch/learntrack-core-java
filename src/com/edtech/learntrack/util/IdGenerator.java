package com.edtech.learntrack.util;

public class IdGenerator {
    private static long studentIdCounter = 1000;
    private static int courseIdCounter = 2000;
    private static int enrollmentIdCounter = 3000;

    private IdGenerator() {}

    public static long getNextStudentId() {
        return ++studentIdCounter;
    }

    public static int getNextCourseId() {
        return ++courseIdCounter;
    }

    public static int getNextEnrollmentId() {
        return ++enrollmentIdCounter;
    }
}
