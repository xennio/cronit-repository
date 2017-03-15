package io.cronit.domain;

import com.couchbase.client.java.repository.annotation.Id;

import java.time.ZonedDateTime;
import java.util.UUID;

public class JobExecutionHistory {

    @Id
    private String id = UUID.randomUUID().toString();

    private String jobModelId;

    private JobExecutionStatus status;

    private String errorMessage;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    public String getId() {
        return id;
    }

    public String getJobModelId() {
        return jobModelId;
    }

    public void setJobModelId(String jobModelId) {
        this.jobModelId = jobModelId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public JobExecutionStatus getStatus() {
        return status;
    }

    public void setStatus(JobExecutionStatus status) {
        this.status = status;
    }
}