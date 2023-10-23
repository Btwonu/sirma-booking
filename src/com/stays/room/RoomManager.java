package com.stays.room;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RoomManager {
    public static Room createRoom(JSONObject roomJObject) {
        int roomNumber = roomJObject.getInt("roomNumber");
        int maximumOccupancy = roomJObject.getInt("maximumOccupancy");
        BigDecimal price = roomJObject.getBigDecimal("price");
        BigDecimal cancellationFee = roomJObject.getBigDecimal("cancellationFee");
        JSONArray amenitiesJArray = roomJObject.getJSONArray("amenities");

        String roomTypeString = roomJObject.getString("type");
        Room.RoomType roomType = Room.RoomType.valueOf(roomTypeString.toUpperCase());
        ArrayList<String> amenitiesList = new ArrayList<>();

        for (int i = 0; i < amenitiesJArray.length(); i++) {
            String amenity = (String) amenitiesJArray.get(i);
            amenitiesList.add(amenity);
        }

        return switch(roomType) {
            case DELUXE -> new DeluxeRoom(
                    roomNumber,
                    maximumOccupancy,
                    price,
                    cancellationFee,
                    amenitiesList
            );
            case SINGLE -> new SingleRoom(
                    roomNumber,
                    maximumOccupancy,
                    price,
                    cancellationFee,
                    amenitiesList
            );
            case DOUBLE -> new DoubleRoom(
                    roomNumber,
                    maximumOccupancy,
                    price,
                    cancellationFee,
                    amenitiesList
            );
            case SUITE -> new SuiteRoom(
                    roomNumber,
                    maximumOccupancy,
                    price,
                    cancellationFee,
                    amenitiesList
            );
        };
    }
}
