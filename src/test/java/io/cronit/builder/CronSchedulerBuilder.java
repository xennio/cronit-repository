package io.cronit.builder;

import io.cronit.domain.CronScheduler;
import io.cronit.domain.ScheduleInfo;

public class CronSchedulerBuilder {

    CronScheduler cronScheduler = new CronScheduler();

    public CronSchedulerBuilder expression(String expression) {
        cronScheduler.setExpression(expression);
        return this;
    }

    public ScheduleInfo build() {
        return cronScheduler;
    }
}
