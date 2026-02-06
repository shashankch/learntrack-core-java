package com.airtribe.learntrack.enums;

public enum StudentStatus {
    ACTIVE(true),
    INACTIVE(false);
    //ADDITIONAL_STATUSES_IF_NEEDED

    private final boolean isActive;

    StudentStatus(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
}