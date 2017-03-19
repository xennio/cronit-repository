package io.cronit.web.vm;

import io.cronit.domain.JobExecutionStatus;

public class JobExecutionHistoryVM {

    private String id;
    private String errorMessage;
    private JobExecutionStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public JobExecutionStatus getStatus() {
        return status;
    }

    public void setStatus(JobExecutionStatus status) {
        this.status = status;
    }
}
