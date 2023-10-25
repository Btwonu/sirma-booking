package com.stays.booking;

import com.stays.auth.AuthService;
import com.stays.util.Config;

import java.nio.file.Path;

public class BookingService {
    private static BookingService instance = null;
    private final Path bookingPath;

    private BookingService() {
        Config config = Config.getInstance();
        this.bookingPath = Path.of(config.getEnv("BOOKING_PATH"));
    }

    public static BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }

        return instance;
    }

    public void book() {

    }
}
