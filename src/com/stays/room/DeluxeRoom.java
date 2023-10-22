package com.stays.room;

import java.math.BigDecimal;

public class DeluxeRoom extends Room {

    public DeluxeRoom(int roomNumber, int maximumOccupancy, BigDecimal price, BigDecimal cancellationFee, String[] amenities) {
        super(roomNumber, maximumOccupancy, price, cancellationFee, amenities);
    }
    @Override
    public void book() {
        System.out.printf("Deluxe room %d is booked%n", this.roomNumber);
    }

    int getRoomNumber() {
        return this.roomNumber;
    }
}
