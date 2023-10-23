package com.stays.util;

import java.util.Scanner;

public class Input {
    String command;
    String[] arguments;

    public Input(String line) {
        String[] array = line.split(" ");
        command = array[0];
        arguments = new String[array.length - 1];

        for (int i = 1; i < array.length; i++) {
            arguments[i - 1] = array[i];
        }
    }

    public String getCommand() {
        return command;
    }

    public String[] getArguments() {
        return arguments;
    }
}