package com.edtech.learntrack.util;

import com.edtech.learntrack.exception.InvalidInputException;

public class InputValidator {

    private InputValidator() {}
    //Overloading usage
    public static void validateInput(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static void validateInput(int value, String fieldName) {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be a positive number.");
        }
    }

    public static void requireEmailLike(String email) {
        if (email == null || email.trim().isEmpty()) return; // optional
        String e = email.trim();
        if (!e.contains("@") || e.startsWith("@") || e.endsWith("@")) {
            throw new InvalidInputException("Email looks invalid.");
        }
    }
}
