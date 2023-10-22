package com.stays.room;

import java.math.BigDecimal;
import java.util.*;

public abstract class Room {
    UUID id;
    int roomNumber;
    int maximumOccupancy;
    BigDecimal price;
    BigDecimal cancellationFee;
    ArrayList<String> amenities;
    ArrayList<Date> bookedDates;

    Room(int roomNumber, int maximumOccupancy, BigDecimal price, BigDecimal cancellationFee, String[] amenities) {
        this.id = UUID.randomUUID();
        this.roomNumber = roomNumber;
        this.maximumOccupancy = maximumOccupancy;
        this.price = price;
        this.cancellationFee = cancellationFee;
        this.amenities = Arrays.asList(amenities);
        this.bookedDates = new ArrayList<>();
    }

    abstract void book();
}