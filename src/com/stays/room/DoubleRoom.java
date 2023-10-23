package com.stays.room;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DoubleRoom extends Room {
    public DoubleRoom(int roomNumber, int maximumOccupancy, BigDecimal price, BigDecimal cancellationFee, ArrayList<String> amenities) {
        super(roomNumber, maximumOccupancy, price, cancellationFee, amenities, RoomType.DELUXE);
    }
    @Override
    public void book() {
        System.out.printf("Deluxe room %d is booked%n", this.roomNumber);
    }

    int getRoomNumber() {
        return this.roomNumber;
    }
}
