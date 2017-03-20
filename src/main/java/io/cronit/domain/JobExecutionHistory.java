package io.cronit.domain;

import com.couchbase.client.java.repository.annotation.Id;
import org.joda.time.DateTime;

import java.util.UUID;

public class JobExecutionHistory {

    @Id
    private String id = UUID.randomUUID().toString();

    private String jobModelId;

    private JobExecutionStatus status;

    private String errorMessage;

    private DateTime startDate;

    private DateTime endDate;

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

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public JobExecutionStatus getStatus() {
        return status;
    }

    public void setStatus(JobExecutionStatus status) {
        this.status = status;
    }
}