import com.stays.auth.AuthService;
import com.stays.util.Input;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
//        Input input = new Input(line);
//
//        String command = input.getCommand();
//        Object[] arguments = input.getArguments();
//
//        System.out.printf("Command: %s, arguments: %s", command, Arrays.toString(arguments));

        System.out.println("Initial loop ran");
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
}