package com.stays.room;

import com.stays.util.DateRange;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class RoomManager {
    public static Room createRoom(JSONObject roomJObject) {
        int roomNumber = roomJObject.getInt("roomNumber");
        int maximumOccupancy = roomJObject.getInt("maximumOccupancy");
        BigDecimal price = roomJObject.getBigDecimal("price");
        BigDecimal cancellationFee = roomJObject.getBigDecimal("cancellationFee");
        JSONArray amenitiesJArray = roomJObject.getJSONArray("amenities");
        JSONArray bookedDatesJArray = roomJObject.getJSONArray("bookedDates");

        String roomTypeString = roomJObject.getString("type");
        Room.RoomType roomType = Room.RoomType.valueOf(roomTypeString.toUpperCase());
        ArrayList<String> amenitiesList = new ArrayList<>();
        ArrayList<DateRange> bookedDatesList = new ArrayList<>();

        for (int i = 0; i < amenitiesJArray.length(); i++) {
            String amenity = (String) amenitiesJArray.get(i);
            amenitiesList.add(amenity);
        }

        for (int i = 0; i < bookedDatesJArray.length(); i++) {
            String dateFormat = "dd.MM.yyyy";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            JSONObject dateRangeJObject = bookedDatesJArray.getJSONObject(i);
            String fromString = dateRangeJObject.getString("from");
            String toString = dateRangeJObject.getString("to");

            LocalDate fromDate = null;
            LocalDate toDate = null;

            try {
                fromDate = LocalDate.parse(fromString, formatter);
                toDate = LocalDate.parse(toString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format.");
                e.printStackTrace();
            }

            bookedDatesList.add(new DateRange(fromDate, toDate));
        }

        return switch(roomType) {
            case DELUXE -> new DeluxeRoom(
                    roomNumber,
                    maximumOccupancy,
                    price,
                    cancellationFee,
                    bookedDatesList,
                    amenitiesList
            );
            case SINGLE -> new SingleRoom(
                    roomNumber,
                    maximumOccupancy,
                    price,
                    cancellationFee,
                    bookedDatesList,
                    amenitiesList
            );
            case DOUBLE -> new DoubleRoom(
                    roomNumber,
                    maximumOccupancy,
                    price,
                    cancellationFee,
                    bookedDatesList,
                    amenitiesList
            );
            case SUITE -> new SuiteRoom(
                    roomNumber,
                    maximumOccupancy,
                    price,
                    cancellationFee,
                    bookedDatesList,
                    amenitiesList
            );
        };
    }
}
