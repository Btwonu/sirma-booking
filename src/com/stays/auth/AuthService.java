package com.stays.auth;

import com.stays.util.Input;
import org.json.JSONArray;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AuthService {
    private static AuthService instance = null;

    private AuthService() {}

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }

        return instance;
    }

    public void initRegistration() {
        Scanner scanner = new Scanner(System.in);

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
                initRegistration();
            } else if (line.equalsIgnoreCase("n")) {
                System.out.println("OK THEN");
            }
        } else {
            System.out.printf("First name: %s%nLast name: %s%nUsername: %s%n", firstName, lastName, username);
            System.out.print("Is this okay? [y/n]: ");
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("y")) {
                register(firstName, lastName, username, password);
            } else if (line.equalsIgnoreCase("n")) {
                System.out.println("OK THEN");
            }
        }

    }

    public void register(String firstName, String lastName, String username, String password) {
        System.out.println("REGISTER");
        Path path = Path.of("C:\\Users\\4s\\IdeaProjects\\booking\\db\\auth\\users.json");

        try {
            String content = Files.readString(path);
            System.out.println(content);
            JSONArray ja = new JSONArray(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Parse data
            // read users.json
            // convert json to users array
        // Conditional
            // check if username exists in array
                // if yes - abort "This username is taken!"
                // try again?
                // if not - Create user
        // Create user
            // generate uuid
            // create user instance
            // add user to users array
            // convert to json
    }

//    public ArrayList<User> getAllUsers() {
//
//    }
}
