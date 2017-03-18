package io.cronit.builder;

import io.cronit.domain.ScheduleInfo;
import io.cronit.domain.TaskScheduler;

import java.time.ZonedDateTime;

public class TaskSchedulerBuilder {

    TaskScheduler taskScheduler = new TaskScheduler();

    public TaskSchedulerBuilder when(ZonedDateTime when) {
        taskScheduler.setWhen(when);
        return this;
    }

    public ScheduleInfo build() {
        return taskScheduler;
    }
}
