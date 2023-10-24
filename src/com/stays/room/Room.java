package com.stays.room;

import com.stays.util.DateRange;

import java.math.BigDecimal;
import java.util.*;

public abstract class Room {
    UUID id;
    int roomNumber;
    int maximumOccupancy;
    BigDecimal price;
    BigDecimal cancellationFee;
    ArrayList<String> amenities;
    ArrayList<DateRange> bookedDates;
    RoomType roomType;
    public enum RoomType {
        SINGLE, DOUBLE, DELUXE, SUITE
    }

    Room(int roomNumber, int maximumOccupancy, BigDecimal price, BigDecimal cancellationFee, ArrayList<DateRange> bookedDates, ArrayList<String> amenities, RoomType roomType) {
        this.id = UUID.randomUUID();
        this.roomNumber = roomNumber;
        this.maximumOccupancy = maximumOccupancy;
        this.price = price;
        this.cancellationFee = cancellationFee;
        this.amenities = amenities;
        this.bookedDates = bookedDates;
        this.roomType = roomType;
    }

    abstract void book();

    public RoomType getRoomType() {
        return this.roomType;
    }

    public ArrayList<DateRange> getBookedDates() {
        return this.bookedDates;
    }

    int getFloor() {
        int x = this.roomNumber;

        while (x > 9) {
            x /= 10;
        }

        return x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("===============================================================%n"));
        sb.append(String.format("Type: %s%n", this.roomType));
        sb.append(String.format("Occupancy: %d%n", this.maximumOccupancy));
        sb.append(String.format("Floor: %d%n", this.getFloor()));
        sb.append(String.format("Has: %s%n", String.join(", ", this.amenities)));
        sb.append(String.format("Price: %.2f%n", this.price));

        return sb.toString();
    }
}