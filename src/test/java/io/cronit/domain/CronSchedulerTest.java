package io.cronit.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CronSchedulerTest {

    @Test
    public void it_should_set_schedule_type_as_cron_in_constructor() {
        CronScheduler cronScheduler = new CronScheduler();
        assertThat(cronScheduler.getScheduleType()).isEqualTo(ScheduleType.CRON);
    }
}