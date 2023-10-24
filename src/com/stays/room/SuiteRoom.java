package com.stays.room;

import com.stays.util.DateRange;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SuiteRoom extends Room {
    public SuiteRoom(int roomNumber, int maximumOccupancy, BigDecimal price, BigDecimal cancellationFee, ArrayList<DateRange> bookedDates, ArrayList<String> amenities) {
        super(roomNumber, maximumOccupancy, price, cancellationFee, bookedDates, amenities, RoomType.SUITE);
    }
    @Override
    public void book() {
        System.out.printf("Deluxe room %d is booked%n", this.roomNumber);
    }

    int getRoomNumber() {
        return this.roomNumber;
    }
}
