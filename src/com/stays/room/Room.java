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
        this.bookedDates = new ArrayList<>();
        this.roomType = roomType;
    }

    abstract void book();

    public RoomType getRoomType() {
        return this.roomType;
    }
}