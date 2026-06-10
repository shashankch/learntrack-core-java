package com.edtech.learntrack.enums;

public enum StudentStatus {
    ACTIVE(true),
    INACTIVE(false);

    private final boolean isActive;

    StudentStatus(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
}