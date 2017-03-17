package io.cronit.web;

import io.cronit.domain.RestJobModel;
import io.cronit.service.JobDefinitionService;
import io.cronit.web.vm.RestCronVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger log = LoggerFactory.getLogger(JobDefinitionResource.class);

    private JobDefinitionService jobDefinitionService;

    @Autowired
    public JobDefinitionResource(JobDefinitionService jobDefinitionService) {
        this.jobDefinitionService = jobDefinitionService;
    }


    @PostMapping("/cron/rest")
    public ResponseEntity createCronRestJob(@RequestBody RestCronVM restCronVM) throws URISyntaxException {
        RestJobModel restJobModel = restCronVM.toRestJobModel();
        jobDefinitionService.register(restCronVM.toRestJobModel());
        return new ResponseEntity(restJobModel, HttpStatus.CREATED);

    }


}
