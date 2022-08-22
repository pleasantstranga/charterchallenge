package com.charter.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static String dateToString(LocalDate date) {
        // Inbuilt format
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        // Custom format if needed
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format LocalDateTime
        return date.format(formatter);
    }
    public static LocalDate convertDateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
