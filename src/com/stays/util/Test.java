package com.stays.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Config config = new Config();

        Path roomsPath = Path.of(config.getEnv("ROOMS_PATH"));

        // TODO: find specific room

//        System.out.println("REGISTER");
//        Path path = Path.of("C:\\Users\\4s\\IdeaProjects\\booking\\db\\auth\\users.json");
//
//        try {
//            String content = Files.readString(path);
//            JSONArray userArray = new JSONArray(content);
//
//            System.out.println(userArray);
//
//            for (int i = 0; i < userArray.length(); i++) {
//                JSONObject jo = userArray.getJSONObject(i);
//
//                System.out.println(jo.get("username"));
//
//                // TODO: Convert each entry and get username || Get them in a list and search
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
