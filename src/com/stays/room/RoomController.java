package com.stays.room;

import com.stays.room.RoomService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public ArrayList<Room> getRoomsInRange(String[] args) {
        String dateFormat = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        LocalDate fromDate = null;
        LocalDate toDate = null;
        try {
            fromDate = LocalDate.parse(args[0], formatter);
            toDate = LocalDate.parse(args[1], formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return new ArrayList<>();
        }

        return roomService.filterRoomsByDate(fromDate, toDate);
    }
}
