package io.cronit.repository;

import io.cronit.domain.JobExecutionHistory;
import org.springframework.data.repository.CrudRepository;

public interface JobExecutionHistoryRepository extends CrudRepository<JobExecutionHistory, String> {

}
