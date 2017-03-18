package io.cronit.web;

import com.codahale.metrics.annotation.Timed;
import io.cronit.domain.RestJobModel;
import io.cronit.service.JobDefinitionService;
import io.cronit.web.vm.RestCronVM;
import io.cronit.web.vm.RestTaskVM;
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
public class JobDefinitionResource {

    private JobDefinitionService jobDefinitionService;

    @Autowired
    public JobDefinitionResource(JobDefinitionService jobDefinitionService) {
        this.jobDefinitionService = jobDefinitionService;
    }

    @PostMapping("/cron/rest")
    @Timed
    public ResponseEntity createCronRestJob(@RequestBody RestCronVM restCronVM) throws URISyntaxException {
        RestJobModel restJobModel = restCronVM.toRestCronJobModel();
        jobDefinitionService.register(restJobModel);
        return new ResponseEntity(restJobModel, HttpStatus.CREATED);
    }

    @PostMapping("/task/rest")
    @Timed
    public ResponseEntity createCronRestJob(@RequestBody RestTaskVM restTaskVM) throws URISyntaxException {
        RestJobModel restJobModel = restTaskVM.toRestTaskJobModel();
        jobDefinitionService.register(restJobModel);
        return new ResponseEntity(restJobModel, HttpStatus.CREATED);
    }
}