package com.stays.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class DateRange {
    LocalDate from;
    LocalDate to;

    public DateRange(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public boolean isIntersecting(DateRange o) {
        long daysBetweenA = from.until(to, ChronoUnit.DAYS);
        long daysBetweenB = o.from.until(o.to, ChronoUnit.DAYS);
        HashMap<LocalDate, Boolean> datesMap = new HashMap<>();

        for (int i = 0; i <= daysBetweenA; i++) {
            LocalDate currentDate = from.plusDays(i);
            datesMap.put(currentDate, true);
        }

        for (int i = 0; i <= daysBetweenB; i++) {
            LocalDate currentDate = o.from.plusDays(i);

            if (datesMap.containsKey(currentDate)) {
                return true;
            }
        }

        return false;
    }
}
