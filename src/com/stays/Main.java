package com.stays;

import com.stays.auth.AuthService;
import com.stays.room.Room;
import com.stays.room.RoomController;
import com.stays.room.RoomService;
import com.stays.util.Input;

import java.io.*;
import java.util.*;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static Map<Integer, Room> currentRoomMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        initialLoop();
    }

    public static void initialLoop() {
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
                RoomService roomService = new RoomService();
                ArrayList<Room> roomsList = roomService.getRoomsList();

                int i = 0;
                for (Room room : roomsList) {
                    System.out.printf("%d. %s", ++i, room);
                }
            }
        }
    }

    public static void loginLoop() {
        AuthService authService = AuthService.getInstance();
        System.out.printf("Welcome %s%n", authService.getUser().getUsername());
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
                System.out.println("To find an available room in a date range, use /filter {start_date} {end_date}. Please enter your dates in a `dd.mm.yyyy` format");
                System.out.println("Use /exit to leave");
            }

            if (input.getCommand().equalsIgnoreCase("/profile")) {
                System.out.println("TODO: Show profile");
            }

            if (input.getCommand().equalsIgnoreCase("/filter")) {
                System.out.println(Arrays.toString(input.getArguments()));
                RoomService roomService = new RoomService();
                RoomController roomController = new RoomController(roomService);
                List<Room> roomsList = roomController.getRoomsInRange(input.getArguments());

                System.out.println("You can book a room by using /book {room_number}");
                int i = 0;
                for (Room room : roomsList) {
                    System.out.printf("%d. %s", ++i, room);
                    currentRoomMap.put(i, room);
                }
            }

            if (input.getCommand().equalsIgnoreCase("/book")) {
                if (currentRoomMap.isEmpty()) {
                    System.out.println("There are no rooms to book!");
                } else {
                    int roomNumber = Integer.parseInt(input.getArguments()[0]);
                    System.out.println(currentRoomMap.get(roomNumber));
                }
            }
        }
    }
}