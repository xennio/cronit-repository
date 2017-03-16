package io.cronit.domain;

import io.cronit.builder.CronSchedulerBuilder;
import io.cronit.builder.RestJobModelBuilder;
import io.cronit.common.CronitSystemException;
import io.cronit.service.JobModelValidationService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class CronSchedulerTest {

    @Test
    public void it_should_set_schedule_type_as_cron_in_constructor() {
        CronScheduler cronScheduler = new CronScheduler();
        assertThat(cronScheduler.getScheduleType()).isEqualTo(ScheduleType.CRON);
    }

    @Test
    public void it_should_throw_system_exception_when_cron_expression_is_not_valid() {
        ScheduleInfo scheduleInfo = new CronSchedulerBuilder().expression("not valid expression").build();
        JobModel jobModel = new RestJobModelBuilder().name("JobName").group("Default").scheduleInfo(scheduleInfo).build();

        JobModelValidationService jobModelValidationService = new JobModelValidationService();

        Throwable thrown = catchThrowable(() -> {
            jobModelValidationService.validate(jobModel);
        });

        CronitSystemException expected = (CronitSystemException) thrown;
        assertThat(expected.getErrorCode()).isEqualTo("expression.not.valid");
        assertThat(expected.getArgs()[0]).isEqualTo("not valid expression");
    }
}