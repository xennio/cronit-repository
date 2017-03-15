package io.cronit.service;

import io.cronit.common.Clock;
import io.cronit.domain.JobExecutionHistory;
import io.cronit.domain.JobExecutionStatus;
import io.cronit.repository.JobExecutionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobExecutionHistoryService {

    private JobExecutionHistoryRepository jobExecutionHistoryRepository;

    @Autowired
    public JobExecutionHistoryService(JobExecutionHistoryRepository jobExecutionHistoryRepository) {
        this.jobExecutionHistoryRepository = jobExecutionHistoryRepository;
    }

    public void start(String jobModelId) {
        JobExecutionHistory jobExecutionHistory = new JobExecutionHistory();
        jobExecutionHistory.setJobModelId(jobModelId);
        jobExecutionHistory.setStatus(JobExecutionStatus.Started);
        jobExecutionHistory.setStartDate(Clock.now());
        jobExecutionHistoryRepository.save(jobExecutionHistory);
    }
}
