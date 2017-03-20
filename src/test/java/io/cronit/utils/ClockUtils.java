package io.cronit.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ClockUtils {
    public static DateTime toLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
        return formatter.parseDateTime(date);
    }
}
