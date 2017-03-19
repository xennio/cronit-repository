package io.cronit.service;

import io.cronit.builder.JobExecutionHistoryBuilder;
import io.cronit.common.Clock;
import io.cronit.domain.JobExecutionHistory;
import io.cronit.domain.JobExecutionStatus;
import io.cronit.repository.JobExecutionHistoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobExecutionHistoryServiceTest {

    @InjectMocks
    private JobExecutionHistoryService jobExecutionHistoryService;

    @Mock
    private JobExecutionHistoryRepository jobExecutionHistoryRepository;

    @Test
    public void it_should_crete_history_record_with_current_date() {
        Clock.freeze();
        JobExecutionHistory jobExecutionHistory = jobExecutionHistoryService.start("jobModelId");

        verify(jobExecutionHistoryRepository).save(jobExecutionHistory);
        assertThat(jobExecutionHistory.getStartDate()).isEqualTo(Clock.now());
        assertThat(jobExecutionHistory.getJobModelId()).isEqualTo("jobModelId");
        assertThat(jobExecutionHistory.getStatus()).isEqualTo(JobExecutionStatus.Started);
        assertThat(jobExecutionHistory.getId()).isNotEmpty();
        Clock.unfreeze();
    }

    @Test
    public void it_should_update_history_record_with_current_date_status_and_error_message() {
        Clock.freeze();
        JobExecutionHistory jobExecutionHistory = JobExecutionHistoryBuilder.aJobHistory().jobModelId("jobModelId").startDate(Clock.now()).status(JobExecutionStatus.Started).build();
        when(jobExecutionHistoryRepository.findOne(jobExecutionHistory.getId())).thenReturn(jobExecutionHistory);

        jobExecutionHistory = jobExecutionHistoryService.update(jobExecutionHistory.getId(), JobExecutionStatus.Failed, "Error Message");

        verify(jobExecutionHistoryRepository).save(jobExecutionHistory);

        assertThat(jobExecutionHistory.getEndDate()).isEqualTo(Clock.now());
        assertThat(jobExecutionHistory.getErrorMessage()).isEqualTo("Error Message");
        assertThat(jobExecutionHistory.getStatus()).isEqualTo(JobExecutionStatus.Failed);
        Clock.unfreeze();
    }
}