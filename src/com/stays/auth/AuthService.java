package com.stays.auth;

import com.stays.Main;
import com.stays.util.Config;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

public class AuthService {
    private static AuthService instance = null;
    private final Path usersPath;
    private final Scanner scanner = new Scanner(System.in);

    private AuthService() {
        Config config = new Config();
        this.usersPath = Path.of(config.getEnv("USERS_PATH"));
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }

        return instance;
    }

    /*
    * Prompt the user for registration details
    */
    public void initRegistration() {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Please confirm your password: ");
        String passwordConfirm = scanner.nextLine();

        if (!password.equals(passwordConfirm)) {
            System.out.println("Provided passwords don't match!");
            System.out.print("Try again? [y/n]: ");

            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("y")) {
                this.initRegistration();
                return;
            } else if (line.equalsIgnoreCase("n")) {
                System.out.println("OK THEN");
            }
        } else {
            System.out.printf("First name: %s%nLast name: %s%nUsername: %s%n", firstName, lastName, username);
            System.out.print("Is this okay? [y/n]: ");
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("y")) {
                this.register(firstName, lastName, username, password);
            } else if (line.equalsIgnoreCase("n")) {
                System.out.println("OK THEN");
            }
        }
    }

    public void initLogin() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        this.login(username, password);
    }

    /*
    * Log a user in
    */
    public void login(String username, String password) {
        // TODO:
        // Look for username in users.json
        // If present:
            // attempt to log in
        // If not:
            // There is no such user!
        HashMap<String, User> usersMap = getUsersMap();

        if (usersMap.get(username) == null) {
            // Username doesn't exist
            System.out.println("There is no such record in our DB");
            System.out.print("Try again? [y/n]: ");

            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("y")) {
                this.initLogin();
                return;
            } else if (line.equalsIgnoreCase("n")) {
                System.out.println("OK THEN");
            }
        } else {
            // Attempt user login
            String storedPassword = usersMap.get(username).getStoredPassword();
            String storedSalt = usersMap.get(username).getStoredSalt();

            boolean passwordsMatch = PasswordHasher.verifyPassword(password, storedPassword, storedSalt);

            if (passwordsMatch) {
                System.out.println("Login successful");
                Main.loginLoop();
            } else {
                System.out.println("Password is incorrect");
            }
        }
    }

    /*
    * Register a new user if the username doesn't exist in db
    */
    public void register(String firstName, String lastName, String username, String password) {
        System.out.println("REGISTER");
        HashMap<String, User> usersMap = getUsersMap();

        if (usersMap.get(username) != null) {
            // Username is taken
            System.out.println("This username is taken!");
            System.out.print("Try again? [y/n]: ");

            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("y")) {
                this.initRegistration();
            } else if (line.equalsIgnoreCase("n")) {
                System.out.println("OK THEN");
            }
        } else {
            // Create new user
            User newUser = new User(firstName, lastName, username);
            JSONObject newUserJObject = new JSONObject(newUser);

            byte[] salt = PasswordHasher.generateSalt();
            byte[] hashedPasswordByteArray = PasswordHasher.hashPassword(password, salt);

            String saltString = Base64.getEncoder().encodeToString(salt);
            String hashedPasswordString = Base64.getEncoder().encodeToString(hashedPasswordByteArray);

            newUserJObject.put("salt", saltString);
            newUserJObject.put("password", hashedPasswordString);

            JSONArray usersJArray = getUsersJArray();
            usersJArray.put(newUserJObject);

            try {
                Files.writeString(this.usersPath.resolve("users.json"), usersJArray.toString(4));
            } catch (IOException e) {
                System.out.println("Cannot write to user json array");
                e.printStackTrace();
            }
        }
    }

    /*
    * Parse users file and return a list of User objects
    */
    public ArrayList<User> getUsersList() {
        ArrayList<User> usersList = new ArrayList<>();

        try {
            String usersContent = Files.readString(this.usersPath.resolve("users.json"));
            JSONArray usersJArray = new JSONArray(usersContent);

            for (int i = 0; i < usersJArray.length(); i++) {
                JSONObject userJ = usersJArray.getJSONObject(i);

                User user = new User(
                        (String) userJ.get("firstName"),
                        (String) userJ.get("lastName"),
                        (String) userJ.get("username")
                );

                usersList.add(user);
            }
        } catch (IOException | JSONException e) {
            System.out.println("IO or JSON bad");
        }

        return usersList;
    }

    /*
    * Parse users file and return a map of User objects
    */
    public HashMap<String, User> getUsersMap() {
        HashMap<String, User> usersMap = new HashMap<>();

        try {
            String usersContent = Files.readString(this.usersPath.resolve("users.json"));
            JSONArray usersJArray = new JSONArray(usersContent);

            for (int i = 0; i < usersJArray.length(); i++) {
                JSONObject userJObject = usersJArray.getJSONObject(i);

                User user = new User(
                        (String) userJObject.get("firstName"),
                        (String) userJObject.get("lastName"),
                        (String) userJObject.get("username")
                );

                usersMap.put((String) userJObject.get("username"), user);
            }
        } catch (IOException | JSONException e) {
            System.out.println("IO or JSON bad");
        }

        return usersMap;
    }

    public JSONArray getUsersJArray() {
        JSONArray usersJArray = null;

        try {
            String usersContent = Files.readString(this.usersPath.resolve("users.json"));
            usersJArray = new JSONArray(usersContent);

        } catch (IOException | JSONException e) {
            System.out.println("IO or JSON bad");
        }

        return usersJArray;
    }

    // Create user
    // Read user
    // Update user
    // Delete user
}
