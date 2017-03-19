package io.cronit.builder;

import io.cronit.domain.JobExecutionStatus;
import io.cronit.web.vm.JobExecutionHistoryVM;

public final class JobExecutionHistoryVMBuilder {
    private String id;
    private String errorMessage;
    private JobExecutionStatus status;

    private JobExecutionHistoryVMBuilder() {
    }

    public static JobExecutionHistoryVMBuilder aJobExecutionHistoryVM() {
        return new JobExecutionHistoryVMBuilder();
    }

    public JobExecutionHistoryVMBuilder id(String id) {
        this.id = id;
        return this;
    }

    public JobExecutionHistoryVMBuilder errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public JobExecutionHistoryVMBuilder status(JobExecutionStatus status) {
        this.status = status;
        return this;
    }

    public JobExecutionHistoryVM build() {
        JobExecutionHistoryVM jobExecutionHistoryVM = new JobExecutionHistoryVM();
        jobExecutionHistoryVM.setId(id);
        jobExecutionHistoryVM.setErrorMessage(errorMessage);
        jobExecutionHistoryVM.setStatus(status);
        return jobExecutionHistoryVM;
    }
}
