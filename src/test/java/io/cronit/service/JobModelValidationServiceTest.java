package io.cronit.service;

import io.cronit.builder.RestJobModelBuilder;
import io.cronit.domain.JobModel;
import io.cronit.domain.ScheduleInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JobModelValidationServiceTest {

    @Mock
    private ScheduleInfo scheduleInfo;

    @Test
    public void it_should_validate_job_model() {
        JobModel jobModel = new RestJobModelBuilder().name("JobName").group("Default").scheduleInfo(scheduleInfo).build();

        JobModelValidationService jobModelValidationService = new JobModelValidationService();
        jobModelValidationService.validate(jobModel);

        verify(scheduleInfo).validate();
    }
}