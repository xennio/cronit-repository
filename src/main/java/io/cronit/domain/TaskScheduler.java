package io.cronit.domain;

import io.cronit.common.Clock;
import io.cronit.common.CronitSystemException;
import org.joda.time.DateTime;

public class TaskScheduler extends ScheduleInfo {
    public TaskScheduler() {
        super(ScheduleType.SINGLE);
    }

    private DateTime when;

    @Override
    public void validate() {
        if (when.compareTo(Clock.now()) < 0) {
            throw new CronitSystemException("task.cannot.before.now", when);
        }
    }

    public DateTime getWhen() {
        return when;
    }

    public void setWhen(DateTime when) {
        this.when = when;
    }
}
