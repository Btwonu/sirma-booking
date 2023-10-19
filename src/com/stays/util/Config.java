package com.stays.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Files.newDirectoryStream;

public class Config {
    HashMap<String, String> vars = new HashMap<>();

    Config() {
        String envPath = "C:\\Users\\4s\\IdeaProjects\\booking\\.env";

        try (BufferedReader br = new BufferedReader(new FileReader(envPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] args = line.split("=");

                vars.put(args[0], args[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getEnv(String var) {
        return vars.get(var);
    }
}
