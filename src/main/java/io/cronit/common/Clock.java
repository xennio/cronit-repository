package io.cronit.common;


import java.time.ZonedDateTime;

public class Clock {

    private static boolean isFrozen;

    private static ZonedDateTime timeSet;

    private Clock() {
    }

    public synchronized static void freeze() {
        isFrozen = true;
    }

    public synchronized static void freeze(ZonedDateTime date) {
        freeze();
        setTime(date);
    }

    public synchronized static void unfreeze() {
        isFrozen = false;
        timeSet = null;
    }

    public static ZonedDateTime now() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        if (isFrozen) {
            if (timeSet == null) {
                timeSet = zonedDateTime;
            }
            return timeSet;
        }

        return zonedDateTime;
    }

    public synchronized static void setTime(ZonedDateTime date) {
        timeSet = date;
    }

}