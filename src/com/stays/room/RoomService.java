package com.stays.room;

import com.stays.auth.User;
import com.stays.room.Room;
import com.stays.util.Config;
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

import static com.stays.room.Room.RoomType.*;

public class RoomService {
    private final Path roomsPath;
    ArrayList<Room> rooms = new ArrayList<>();

    public RoomService() {
        Config config = new Config();
        this.roomsPath = Path.of(config.getEnv("ROOMS_PATH"));
    }

    public void loadRooms(Path path) {
        System.out.printf("Load rooms from: %s", path);
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

    public ArrayList<Room> filterRoomsByDate(LocalDate from, LocalDate to) {
        ArrayList<Room> roomsList = getRoomsList();
        // iterate over rooms
        // f/e room iterate over room date ranges
        // check if date range passed from user is intersecting with any of the date ranges of the room
        return new ArrayList<>();
    }

    // Create room (admin)
    // get room details
    // instantiate room
    // create a new room file and write

    // Read rooms
    // iterate over rooms directory
    // f/e room, read room
    // instantiate room
    // add room to list/map

    // Update room (admin | booking)
    // get (updated) room details
    // find room file in rooms directory
    // overwrite file with new data

    // Delete room (admin)
    // find room file in rooms directory
    // delete file

    // Filters

    // available in date range
    // get user input
    // get room list
    // filter rooms available in date range

    // in price range

    // by room type

    // containing amenities

}
