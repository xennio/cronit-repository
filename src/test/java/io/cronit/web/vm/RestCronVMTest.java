package io.cronit.web.vm;

import io.cronit.builder.RestCronVMBuilder;
import io.cronit.domain.CronScheduler;
import io.cronit.domain.RestJobModel;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RestCronVMTest {

    @Test
    public void it_should_create_rest_job_model_from_rest_cron_vm() {


        RestCronVM restCronVM = RestCronVMBuilder.aRestCronVM()
                .id(UUID.randomUUID().toString())
                .body("body").expectedStatus(200)
                .group("group")
                .expression("* * * * *")
                .method("GET")
                .name("name")
                .url("http://url")
                .addHeader("foo","bar")
                .build();

        RestJobModel restJobModel = restCronVM.toRestJobModel();

        assertThat(restJobModel.getId()).isEqualTo(restCronVM.getId());
        assertThat(restJobModel.getBody()).isEqualTo("body");
        assertThat(restJobModel.getExpectedStatus()).isEqualTo(200);
        assertThat(restJobModel.getHeaders()).isEqualTo(restCronVM.getHeaders());
        assertThat(restJobModel.getMethod()).isEqualTo("GET");
        assertThat(restJobModel.getGroup()).isEqualTo("group");
        assertThat(restJobModel.getName()).isEqualTo("name");
        assertThat(restJobModel.getUrl()).isEqualTo("http://url");
        assertThat(restJobModel.getScheduleInfo()).isInstanceOf(CronScheduler.class);
        assertThat(((CronScheduler) restJobModel.getScheduleInfo()).getExpression()).isEqualTo("* * * * *");

    }

}