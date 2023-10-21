package com.stays.auth;

import java.util.UUID;

public class User {
    public UUID id;
    public String firstName;
    public String lastName;
    public String username;

    User(String firstName, String lastName, String username) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public String getId() {
        return this.id.toString();
    }

    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
