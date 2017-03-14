package io.cronit.domain;

public abstract class ScheduleInfo {

    public ScheduleInfo(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    private ScheduleType scheduleType;

}
