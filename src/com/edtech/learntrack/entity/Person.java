package com.edtech.learntrack.entity;

import com.edtech.learntrack.util.InputValidator;
import java.util.Objects;

public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    public Person() {}

    public Person(long id, String firstName, String lastName, String email) {
        InputValidator.requireEmailLike(email);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        String fn = Objects.requireNonNullElse(firstName, "").trim();
        String ln = Objects.requireNonNullElse(lastName, "").trim();
        String name = (fn + " " + ln).trim();
        return name.isEmpty() ? ("Person#" + id) : name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName  +
                ", email=" + email +
                "}";
    }
}
