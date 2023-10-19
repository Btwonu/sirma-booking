package com.stays.util;

import com.stays.room.DeluxeRoom;
import com.stays.room.Room;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOTest {
    public static void main(String[] args) throws IOException {
        System.out.println(FileSystems.getDefault().toString());

        DeluxeRoom deluxeRoom =  new DeluxeRoom(101);
        deluxeRoom.book();

        try {
            byte[] content = Files.readAllBytes(Path.of("C:\\Users\\4s\\IdeaProjects\\booking\\db\\deluxe-101.json"));
            JSONObject json = new JSONObject(new String(content));

            System.out.println(json.getInt("pricePerNight"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
