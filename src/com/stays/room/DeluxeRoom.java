package com.stays.room;

public class DeluxeRoom extends Room {

    public DeluxeRoom(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    @Override
    public void book() {
        System.out.printf("Deluxe room %d is booked%n", this.roomNumber);
    }

    int getRoomNumber() {
        return this.roomNumber;
    }
}
