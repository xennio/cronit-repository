package io.cronit.domain;

import io.cronit.common.Clock;
import io.cronit.common.CronitSystemException;

import java.time.ZonedDateTime;

public class TaskScheduler extends ScheduleInfo {
    public TaskScheduler() {
        super(ScheduleType.SINGLE);
    }

    private ZonedDateTime when;

    @Override
    public void validate() {
        if (when.compareTo(Clock.now()) < 0) {
            throw new CronitSystemException("task.cannot.before.now", when);
        }
    }

    public ZonedDateTime getWhen() {
        return when;
    }

    public void setWhen(ZonedDateTime when) {
        this.when = when;
    }
}
