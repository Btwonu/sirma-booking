package com.stays;

import com.stays.auth.AuthService;
import com.stays.room.Room;
import com.stays.room.RoomController;
import com.stays.room.RoomService;
import com.stays.util.Input;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        String line = scanner.nextLine();
//        Input input = new Input(line);
//
//        String command = input.getCommand();
//        Object[] arguments = input.getArguments();
//
//        System.out.printf("Command: %s, arguments: %s", command, Arrays.toString(arguments));

        System.out.println("If you need help, please type /help");

        while (true) {
            // get input
            String line = scanner.nextLine();

            if (line.equals("/exit")) {
                break;
            }

            Input input = new Input(line);

            if (input.getCommand().equalsIgnoreCase("/help")) {
                System.out.println("If you're a new user, use /register to register");
                System.out.println("If you already have an account, use /login to login");
                System.out.println("To see our room selection, please use /rooms");
                System.out.println("Use /exit to leave");
            }

            if (input.getCommand().equalsIgnoreCase("/register")) {
                AuthService authService = AuthService.getInstance();
                authService.initRegistration();
            }

            if (input.getCommand().equalsIgnoreCase("/login")) {
                AuthService authService = AuthService.getInstance();
                authService.initLogin();
            }

            if (input.getCommand().equalsIgnoreCase("/rooms")) {
                System.out.println("ROOMS");
            }
        }
    }

    public static void loginLoop() {
        System.out.println("If you need help, please type /help");

        while (true) {
            // get input
            String line = scanner.nextLine();

            if (line.equals("/exit")) {
                break;
            }

            Input input = new Input(line);

            if (input.getCommand().equalsIgnoreCase("/help")) {
                System.out.println("To review your profile, use /profile");
                System.out.println("To book a room in a date range, use /book {start_date} {end_date}. Please enter your dates in a `dd.mm.yyyy format`");
                System.out.println("Use /exit to leave");
            }

            if (input.getCommand().equalsIgnoreCase("/profile")) {
                System.out.println("TODO: Show profile");
            }

            if (input.getCommand().equalsIgnoreCase("/book")) {
                System.out.println(Arrays.toString(input.getArguments()));
                RoomService roomService = new RoomService();
                RoomController roomController = new RoomController(roomService);
                roomController.getRoomsInRange(input.getArguments());
            }
        }
    }
}