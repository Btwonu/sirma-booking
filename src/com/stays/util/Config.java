package com.stays.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Config {
    private static Config instance = null;
    HashMap<String, String> vars = new HashMap<>();

    private Config() {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path envPath = Paths.get(root.toString(),".env");

        try (BufferedReader br = new BufferedReader(new FileReader(envPath.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] args = line.split("=");

                vars.put(args[0], args[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }

        return instance;
    }

    public String getEnv(String var) {
        return vars.get(var);
    }
}
