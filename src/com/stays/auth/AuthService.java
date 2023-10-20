package com.stays.auth;

import com.stays.util.Config;
import com.stays.util.Input;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class AuthService {
    private static AuthService instance = null;
    private final Path usersPath;

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


        // TODO: Resolve users.json


        // Parse data
        // read users.json
        // convert json to users array
        ArrayList<User> usersList = getAllUsers();
        HashMap<String, User> usersMap = getUsersMap();

        // Conditional
        // check if username exists in array
        // if yes - abort "This username is taken!"
        // try again?
        // if not - Create user
        if (usersMap.get(username) != null) {
            System.out.println("This username is taken!");
        } else {
            // Create user
            // generate uuid
            // create user instance
            // add user to users array
            // convert to json
        }
    }

    /*
    * Parse users.json file and
    * return a list of User objects
    */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<>();

        try {
            String usersContent = Files.readString(this.usersPath);
            System.out.println(usersContent);
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

    public HashMap<String, User> getUsersMap() {
        HashMap<String, User> usersMap = new HashMap<>();

        try {
            String usersContent = Files.readString(this.usersPath);
            System.out.println(usersContent);
            JSONArray usersJArray = new JSONArray(usersContent);

            for (int i = 0; i < usersJArray.length(); i++) {
                JSONObject userJ = usersJArray.getJSONObject(i);

                User user = new User(
                        (String) userJ.get("firstName"),
                        (String) userJ.get("lastName"),
                        (String) userJ.get("username")
                );

                usersMap.put((String) userJ.get("username"), user);
            }
        } catch (IOException | JSONException e) {
            System.out.println("IO or JSON bad");
        }

        return usersMap;
    }
}
