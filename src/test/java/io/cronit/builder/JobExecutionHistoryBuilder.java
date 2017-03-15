package io.cronit.builder;

import io.cronit.domain.JobExecutionHistory;
import io.cronit.domain.JobExecutionStatus;

import java.time.ZonedDateTime;

public class JobExecutionHistoryBuilder {

    JobExecutionHistory jobExecutionHistory = new JobExecutionHistory();

    public JobExecutionHistoryBuilder jobModelId(String jobModelId) {
        jobExecutionHistory.setJobModelId(jobModelId);
        return this;
    }

    public JobExecutionHistoryBuilder status(JobExecutionStatus status) {
        jobExecutionHistory.setStatus(status);
        return this;
    }

    public JobExecutionHistoryBuilder errorMessage(String errorMessage) {
        jobExecutionHistory.setErrorMessage(errorMessage);
        return this;
    }

    public JobExecutionHistoryBuilder startDate(ZonedDateTime startDate) {
        jobExecutionHistory.setStartDate(startDate);
        return this;
    }

    public JobExecutionHistoryBuilder endDate(ZonedDateTime endDate) {
        jobExecutionHistory.setEndDate(endDate);
        return this;
    }

    public JobExecutionHistory build() {
        return jobExecutionHistory;
    }
}
