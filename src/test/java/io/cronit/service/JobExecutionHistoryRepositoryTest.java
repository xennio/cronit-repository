package io.cronit.service;

import io.cronit.common.Clock;
import io.cronit.domain.JobExecutionHistory;
import io.cronit.domain.JobExecutionStatus;
import io.cronit.repository.JobExecutionHistoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JobExecutionHistoryRepositoryTest {

    @InjectMocks
    private JobExecutionHistoryService jobExecutionHistoryService;

    @Mock
    private JobExecutionHistoryRepository jobExecutionHistoryRepository;

    @Test
    public void it_should_insert_job_execution_when_job_started() {
        ArgumentCaptor<JobExecutionHistory> jobExecutionHistoryArgumentCaptor = ArgumentCaptor.forClass(JobExecutionHistory.class);
        Clock.freeze();
        Clock.setTime(ZonedDateTime.of(2017, 3, 15, 0, 0, 0, 0, ZoneId.of("GMT")));
        jobExecutionHistoryService.start("JobId");

        verify(jobExecutionHistoryRepository).save(jobExecutionHistoryArgumentCaptor.capture());

        JobExecutionHistory jobExecutionHistory = jobExecutionHistoryArgumentCaptor.getValue();

        assertThat(jobExecutionHistory.getStatus()).isEqualTo(JobExecutionStatus.Started);
        assertThat(jobExecutionHistory.getJobModelId()).isEqualTo("JobId");
        assertThat(jobExecutionHistory.getStartDate()).isEqualTo(Clock.now());
        Clock.unfreeze();
    }
}