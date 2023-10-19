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
}
