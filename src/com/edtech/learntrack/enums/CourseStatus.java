package com.edtech.learntrack.enums;

public enum CourseStatus {
    ACTIVE(true),
    INACTIVE(false);
    //ADDITIONAL_STATUSES_IF_NEEDED

    private final boolean isActive;

    CourseStatus(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
}
