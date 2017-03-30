package io.cronit.service;

import io.cronit.builder.CronSchedulerBuilder;
import io.cronit.builder.RestJobModelBuilder;
import io.cronit.common.CronitBusinessException;
import io.cronit.domain.JobModel;
import io.cronit.domain.ScheduleInfo;
import io.cronit.repository.JobDefinitionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class JobDefinitionServiceTest {

    @InjectMocks
    private JobDefinitionService jobDefinitionService;

    @Mock
    private JobDefinitionRepository jobDefinitionRepository;

    @Mock
    private HashService hashService;

    @Mock
    private JobModelValidationService jobModelValidationService;

    @Test
    public void it_should_throw_business_exception_when_job_with_same_name_is_already_defined() {
        ScheduleInfo scheduleInfo = new CronSchedulerBuilder().expression("* * * * *").build();
        JobModel jobModel = new RestJobModelBuilder().name("JobName").group("Default").scheduleInfo(scheduleInfo).build();

        String companyId = UUID.randomUUID().toString();
        String hashedJobId = UUID.randomUUID().toString();

        Mockito.when(hashService.toMd5("JobName")).thenReturn(hashedJobId);

        Mockito.when(jobDefinitionRepository.findOne(hashedJobId)).thenReturn(jobModel);

        Throwable thrown = catchThrowable(() -> {
            jobDefinitionService.register(jobModel);
        });

        verifyZeroInteractions(jobModelValidationService);

        CronitBusinessException expected = (CronitBusinessException) thrown;
        assertThat(expected.getErrorCode()).isEqualTo("job.already.exists");
        assertThat(expected.getArgs()[0].toString()).isEqualTo("JobName");

    }


    @Test
    public void it_should_try_to_save_job_model_when_job_is_not_defined_before() {
        ScheduleInfo scheduleInfo = new CronSchedulerBuilder().expression("* * * * *").build();
        JobModel jobModel = new RestJobModelBuilder().name("JobName").group("Default").scheduleInfo(scheduleInfo).build();

        String companyId = UUID.randomUUID().toString();
        String hashedJobId = UUID.randomUUID().toString();

        Mockito.when(hashService.toMd5("JobName")).thenReturn(hashedJobId);

        Mockito.when(jobDefinitionRepository.findOne("JobName")).thenReturn(null);

        jobDefinitionService.register(jobModel);

        verify(jobModelValidationService).validate(jobModel);
        verify(jobDefinitionRepository).save(jobModel);
        assertThat(jobModel.getId()).isEqualTo(hashedJobId);
    }
}