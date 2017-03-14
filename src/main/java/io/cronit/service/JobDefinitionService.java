package io.cronit.service;

import io.cronit.common.CronitBusinessException;
import io.cronit.domain.JobModel;
import io.cronit.repository.JobDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobDefinitionService {

    private JobDefinitionRepository jobDefinitionRepository;

    private HashService hashService;

    private AuthenticationService authenticationService;

    @Autowired
    public JobDefinitionService(JobDefinitionRepository jobDefinitionRepository,
                                HashService hashService,
                                AuthenticationService authenticationService) {
        this.jobDefinitionRepository = jobDefinitionRepository;
        this.hashService = hashService;
        this.authenticationService = authenticationService;
    }

    public void register(JobModel jobModel) {
        String currentCompanyId = authenticationService.getCurrentCompanyId();
        String jobIdHash = hashService.toMd5(jobModel.getName(), currentCompanyId);
        JobModel found = jobDefinitionRepository.findOne(jobIdHash);
        if (found == null) {
            jobModel.setId(jobIdHash);
            jobDefinitionRepository.save(jobModel);
        } else {
            throw new CronitBusinessException("job.already.exists", jobModel.getName());
        }
    }
}