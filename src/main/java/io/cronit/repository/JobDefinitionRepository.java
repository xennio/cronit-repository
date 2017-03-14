package io.cronit.repository;

import io.cronit.domain.JobModel;
import org.springframework.data.repository.CrudRepository;

public interface JobDefinitionRepository extends CrudRepository<JobModel, String> {
}