package com.stays.util;

import java.util.Scanner;

public class Input {
    String command;
    Object[] arguments;

    public Input(String line) {
        String[] array = line.split(" ");
        command = array[0];
        arguments = new Object[array.length - 1];

        for (int i = 1; i < array.length; i++) {
            try {
                arguments[i - 1] = Integer.parseInt(array[i]);
            } catch (NumberFormatException e) {
                arguments[i - 1] = array[i];
            }
        }
    }

    public String getCommand() {
        return command;
    }

    public Object[] getArguments() {
        return arguments;
    }
}