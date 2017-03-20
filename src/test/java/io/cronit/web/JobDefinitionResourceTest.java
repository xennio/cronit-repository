package io.cronit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import io.cronit.CronitRepositoryApplication;
import io.cronit.builder.RestCronVMBuilder;
import io.cronit.builder.RestTaskVmBuilder;
import io.cronit.common.Clock;
import io.cronit.domain.JobModel;
import io.cronit.service.JobDefinitionService;
import io.cronit.utils.ClockUtils;
import io.cronit.web.vm.RestCronVM;
import io.cronit.web.vm.RestTaskVM;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CronitRepositoryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobDefinitionResourceTest {

    @Mock
    private JobDefinitionService jobDefinitionService;

    private MockMvc restJobDefinitionMockMvc;

    private JacksonTester<RestCronVM> restCronTester;

    private JacksonTester<RestTaskVM> restTaskTester;

    @Before
    public void setup() {
        JobDefinitionResource jobDefinitionResource = new JobDefinitionResource(jobDefinitionService);
        this.restJobDefinitionMockMvc = MockMvcBuilders.standaloneSetup(jobDefinitionResource).build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.
                WRITE_DATES_AS_TIMESTAMPS, false);
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void it_should_create_rest_job_with_cron_expression() throws Exception {
        RestCronVM restCronVM = RestCronVMBuilder.aSampleCronVM();

        String jsonBody = restCronTester.write(restCronVM).getJson();

        restJobDefinitionMockMvc.perform(post("/api/cron/rest").content(jsonBody)
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("{\"id\":\"" + restCronVM.getId() + "\",\"name\":\"SampleJob\",\"group\":\"group\",\"scheduleInfo\":{\"scheduleType\":\"CRON\",\"expression\":\"* * * * *\"},\"method\":\"GET\",\"url\":\"http://url\",\"headers\":{\"foo\":\"bar\",\"key\":\"val\"},\"body\":\"body\",\"expectedStatus\":200}"));

        verify(jobDefinitionService).register(any(JobModel.class));
    }

    @Test
    public void it_should_schedule_single_rest_task() throws Exception {
        RestTaskVM restTaskVm = RestTaskVmBuilder.aSampleTaskVM(ClockUtils.toLocalDate("20180823"));

        String jsonBody = restTaskTester.write(restTaskVm).getJson();

        restJobDefinitionMockMvc.perform(post("/api/task/rest").content(jsonBody)
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("{\"id\":\"" + restTaskVm.getId() + "\",\"name\":\"SampleJob\",\"group\":\"group\",\"scheduleInfo\":{\"scheduleType\":\"SINGLE\",\"when\":" + restTaskVm.getWhen().getMillis() + "},\"method\":\"GET\",\"url\":\"http://url\",\"headers\":{\"foo\":\"bar\",\"key\":\"val\"},\"body\":\"body\",\"expectedStatus\":200}"));

        verify(jobDefinitionService).register(any(JobModel.class));
        Clock.unfreeze();
    }
}