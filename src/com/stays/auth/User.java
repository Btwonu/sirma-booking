package com.stays.auth;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.NoSuchElementException;
import java.util.UUID;

public class User {
    public UUID id;
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public String salt;

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

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStoredPassword() {
        JSONArray usersJArray = AuthService.getInstance().getUsersJArray();

        for (int i = 0; i < usersJArray.length(); i++) {
            JSONObject userJObject = usersJArray.getJSONObject(i);

            if (userJObject.has("username") && userJObject.get("username").equals(this.username)) {
                return (String) userJObject.get("password");
            }
        }

        throw new NoSuchElementException("Item not found.");
    }

    public String getStoredSalt() {
        JSONArray usersJArray = AuthService.getInstance().getUsersJArray();

        for (int i = 0; i < usersJArray.length(); i++) {
            JSONObject userJObject = usersJArray.getJSONObject(i);

            if (userJObject.has("username") && userJObject.get("username").equals(this.username)) {
                return (String) userJObject.get("salt");
            }
        }

        throw new NoSuchElementException("Item not found.");
    }
}
