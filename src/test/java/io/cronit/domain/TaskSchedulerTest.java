package io.cronit.domain;

import io.cronit.builder.TaskSchedulerBuilder;
import io.cronit.common.Clock;
import io.cronit.common.CronitSystemException;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class TaskSchedulerTest {
    @Test
    public void it_should_set_schedule_type_as_single_in_constructor() {
        TaskScheduler taskScheduler = new TaskScheduler();
        assertThat(taskScheduler.getScheduleType()).isEqualTo(ScheduleType.SINGLE);
    }

    @Test
    public void it_should_not_throw_any_exception_when_task_date_after_now() {
        ZonedDateTime taskDate = ZonedDateTime.of(2020, 3, 18, 0, 0, 0, 0, ZoneId.of("UTC"));
        Clock.freeze(ZonedDateTime.of(2017, 3, 18, 0, 0, 0, 0, ZoneId.of("UTC")));
        ScheduleInfo scheduleInfo = new TaskSchedulerBuilder().when(taskDate).build();
        scheduleInfo.validate();
        Clock.unfreeze();
    }

    @Test
    public void it_should_throw_system_exception_when_task_date_before_now() {
        ZonedDateTime taskDate = ZonedDateTime.of(2015, 3, 18, 0, 0, 0, 0, ZoneId.of("UTC"));

        Clock.freeze(ZonedDateTime.of(2017, 3, 18, 0, 0, 0, 0, ZoneId.of("UTC")));
        ScheduleInfo scheduleInfo = new TaskSchedulerBuilder().when(taskDate).build();

        Throwable thrown = catchThrowable(() -> {
            scheduleInfo.validate();
        });

        CronitSystemException expected = (CronitSystemException) thrown;
        assertThat(expected.getErrorCode()).isEqualTo("task.cannot.before.now");
        assertThat(expected.getArgs()[0]).isEqualTo(taskDate);
        Clock.unfreeze();
    }
}