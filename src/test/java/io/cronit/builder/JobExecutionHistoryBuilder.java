package io.cronit.builder;

import io.cronit.domain.JobExecutionHistory;
import io.cronit.domain.JobExecutionStatus;
import org.joda.time.DateTime;

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

    public JobExecutionHistoryBuilder startDate(DateTime startDate) {
        jobExecutionHistory.setStartDate(startDate);
        return this;
    }

    public JobExecutionHistoryBuilder endDate(DateTime endDate) {
        jobExecutionHistory.setEndDate(endDate);
        return this;
    }

    public JobExecutionHistory build() {
        return jobExecutionHistory;
    }

    public static JobExecutionHistoryBuilder aJobHistory() {
        return new JobExecutionHistoryBuilder();
    }
}
