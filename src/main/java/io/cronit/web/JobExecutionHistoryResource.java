package io.cronit.web;


import com.codahale.metrics.annotation.Timed;
import io.cronit.domain.JobExecutionHistory;
import io.cronit.service.JobExecutionHistoryService;
import io.cronit.web.vm.JobExecutionHistoryVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class JobExecutionHistoryResource {

    private JobExecutionHistoryService jobExecutionHistoryService;

    @Autowired
    public JobExecutionHistoryResource(JobExecutionHistoryService jobExecutionHistoryService) {
        this.jobExecutionHistoryService = jobExecutionHistoryService;
    }

    @PostMapping("/history/start")
    @Timed
    public ResponseEntity startJob(@RequestBody JobExecutionHistoryVM jobExecutionHistoryVM) throws URISyntaxException {
        JobExecutionHistory jobExecutionHistory = jobExecutionHistoryService.start(jobExecutionHistoryVM.getId());
        return new ResponseEntity(jobExecutionHistory, HttpStatus.CREATED);
    }

    @PostMapping("/history/finish")
    @Timed
    public ResponseEntity finishJob(@RequestBody JobExecutionHistoryVM jobExecutionHistoryVM) throws URISyntaxException {
        JobExecutionHistory jobExecutionHistory = jobExecutionHistoryService
                .update(jobExecutionHistoryVM.getId(), jobExecutionHistoryVM.getStatus(), jobExecutionHistoryVM.getErrorMessage());

        return new ResponseEntity(jobExecutionHistory, HttpStatus.CREATED);
    }
}