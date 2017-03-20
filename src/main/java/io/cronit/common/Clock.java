package io.cronit.common;


import org.joda.time.DateTime;

import java.util.Date;

public final class Clock {

    private static boolean isFrozen;

    private static DateTime timeSet;

    private Clock() {
    }

    public synchronized static void freeze() {
        isFrozen = true;
    }

    public synchronized static void freeze(DateTime date) {
        freeze();
        setTime(date);
    }

    public synchronized static void unfreeze() {
        isFrozen = false;
        timeSet = null;
    }

    public static DateTime now() {
        DateTime dateTime = DateTime.now();
        if (isFrozen) {
            if (timeSet == null) {
                timeSet = dateTime;
            }
            return timeSet;
        }

        return dateTime;
    }

    public synchronized static void setTime(DateTime date) {
        timeSet = date;
    }


    public static boolean isAfterNow(Date date) {
        return Clock.now().isAfter(new DateTime(date));
    }

    public static boolean isBeforeNow(Date date) {
        return !isAfterNow(date);
    }
}