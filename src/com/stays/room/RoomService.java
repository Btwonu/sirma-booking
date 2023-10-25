package com.stays.room;

import com.stays.util.Config;
import com.stays.util.DateRange;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.stays.room.Room.RoomType.*;

public class RoomService {
    private static RoomService instance = null;
    private final Path roomsPath;
    ArrayList<Room> rooms = new ArrayList<>();

    public RoomService() {
        Config config = Config.getInstance();
        this.roomsPath = Path.of(config.getEnv("ROOMS_PATH"));
    }

    public static RoomService getInstance() {
        if (instance == null) {
            instance = new RoomService();
        }

        return instance;
    }

    public ArrayList<Room> getRoomsList() {
        ArrayList<Room> roomsList = new ArrayList<>();

        if (!Files.isDirectory(this.roomsPath)) {
            System.err.println("The specified path is not a directory.");
            return roomsList;
        }

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(this.roomsPath, "*.json")) {
            for (Path filePath : directoryStream) {
                String roomContent = Files.readString(filePath);
                JSONObject roomJObject = new JSONObject(roomContent);

                Room newRoom = RoomManager.createRoom(roomJObject);

                roomsList.add(newRoom);
            }
        } catch (IOException | JSONException e) {
            System.out.println("IO or JSON bad");
            e.printStackTrace();
        }

        return roomsList;
    }

    public List<Room> filterRoomsByDate(LocalDate from, LocalDate to) {
        ArrayList<Room> roomsList = getRoomsList();
        DateRange userDateRange = new DateRange(from, to);

        return roomsList.stream().filter(r -> {
            for (DateRange dr : r.getBookedDates()) {
                if (userDateRange.isIntersecting(dr)) {
                    return false;
                }
            }

            return true;
        }).toList();
    }
}
