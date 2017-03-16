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

    private JobModelValidationService jobModelValidationService;

    @Autowired
    public JobDefinitionService(JobDefinitionRepository jobDefinitionRepository,
                                HashService hashService,
                                AuthenticationService authenticationService,
                                JobModelValidationService jobModelValidationService) {
        this.jobDefinitionRepository = jobDefinitionRepository;
        this.hashService = hashService;
        this.authenticationService = authenticationService;
        this.jobModelValidationService = jobModelValidationService;
    }

    public void register(JobModel jobModel) {
        String currentCompanyId = authenticationService.getCurrentIdentity();
        String jobIdHash = hashService.toMd5(jobModel.getName(), currentCompanyId);
        JobModel found = jobDefinitionRepository.findOne(jobIdHash);
        if (found == null) {
            jobModelValidationService.validate(jobModel);
            jobModel.setId(jobIdHash);
            jobDefinitionRepository.save(jobModel);
        } else {
            throw new CronitBusinessException("job.already.exists", jobModel.getName());
        }
    }
}