package com.stays.room;

import com.stays.room.Room;

import java.nio.file.Path;
import java.util.ArrayList;

public class RoomService {
    ArrayList<Room> rooms = new ArrayList<>();

    public void loadRooms(Path path) {
        System.out.printf("Load rooms from: %s", path);
    }

    public ArrayList<Room> listRooms() {
        return this.rooms;
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

    // Filter by type
    // 
}
