package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.StudentStatus;

public class Student extends Person {
    private String batch;
    private StudentStatus status;

    public Student() {}

    // Constructor overloading example: without email
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, null);
        this.batch = batch;
        this.status = StudentStatus.ACTIVE;
    }

    public Student(int id, String firstName, String lastName, String email, String batch, StudentStatus status) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.status = status;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Student)";
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name=" + getDisplayName() +
                ", email=" + getEmail()+
                ", batch=" + getBatch() +
                ", status=" + status.toString() +
                "}";
    }
}
