package io.cronit.builder;

import io.cronit.domain.ScheduleInfo;
import io.cronit.domain.TaskScheduler;
import org.joda.time.DateTime;

public class TaskSchedulerBuilder {

    TaskScheduler taskScheduler = new TaskScheduler();

    public TaskSchedulerBuilder when(DateTime when) {
        taskScheduler.setWhen(when);
        return this;
    }

    public ScheduleInfo build() {
        return taskScheduler;
    }
}
