package io.cronit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cronit.CronitRepositoryApplication;
import io.cronit.builder.JobExecutionHistoryBuilder;
import io.cronit.builder.JobExecutionHistoryVMBuilder;
import io.cronit.common.Clock;
import io.cronit.domain.JobExecutionHistory;
import io.cronit.domain.JobExecutionStatus;
import io.cronit.service.JobExecutionHistoryService;
import io.cronit.web.vm.JobExecutionHistoryVM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CronitRepositoryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobExecutionHistoryResourceTest {

    private MockMvc restJobExecutionHistoryMockMvc;

    @Mock
    private JobExecutionHistoryService jobExecutionHistoryService;

    private JacksonTester<JobExecutionHistory> jobExecutionHistoryJacksonTester;

    private JacksonTester<JobExecutionHistoryVM> jobExecutionHistoryVMJacksonTester;

    @Before
    public void setup() {
        JobExecutionHistoryResource jobExecutionHistoryResource = new JobExecutionHistoryResource(jobExecutionHistoryService);
        restJobExecutionHistoryMockMvc = MockMvcBuilders.standaloneSetup(jobExecutionHistoryResource).build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void it_should_return_job_execution_history_record_when_job_started() throws Exception {

        JobExecutionHistory jobExecutionHistory = JobExecutionHistoryBuilder.aJobHistory().startDate(Clock.now()).status(JobExecutionStatus.Started).build();
        when(jobExecutionHistoryService.start("jobId")).thenReturn(jobExecutionHistory);

        String response = restJobExecutionHistoryMockMvc.perform(post("/api/history/start").content("{\"id\":\"jobId\"}")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        JobExecutionHistory expected = jobExecutionHistoryJacksonTester.parse(response).getObject();

        assertThat(expected.getId()).isEqualTo(jobExecutionHistory.getId());
        assertThat(expected.getStatus()).isEqualTo(jobExecutionHistory.getStatus());

    }

    @Test
    public void it_should_update_job_execution_history_record_when_job_finished() throws Exception {

        JobExecutionHistoryVM jobExecutionHistoryVM = JobExecutionHistoryVMBuilder.aJobExecutionHistoryVM().id("jobId")
                .status(JobExecutionStatus.Failed).errorMessage("Error Message").build();

        JobExecutionHistory jobExecutionHistory = JobExecutionHistoryBuilder.aJobHistory().endDate(Clock.now()).errorMessage("Error Message")
                .startDate(Clock.now()).status(JobExecutionStatus.Started).build();

        when(jobExecutionHistoryService.update(jobExecutionHistoryVM.getId(), JobExecutionStatus.Failed, "Error Message"))
                .thenReturn(jobExecutionHistory);

        String response = restJobExecutionHistoryMockMvc.perform(post("/api/history/finish")
                .content(jobExecutionHistoryVMJacksonTester.write(jobExecutionHistoryVM).getJson())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        JobExecutionHistory expected = jobExecutionHistoryJacksonTester.parse(response).getObject();

        assertThat(expected.getId()).isEqualTo(jobExecutionHistory.getId());
        assertThat(expected.getStatus()).isEqualTo(jobExecutionHistory.getStatus());

    }
}