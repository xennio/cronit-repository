package io.cronit.web.vm;

import io.cronit.builder.RestTaskVmBuilder;
import io.cronit.common.Clock;
import io.cronit.domain.RestJobModel;
import io.cronit.domain.TaskScheduler;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RestTaskVMTest {

    @Test
    public void it_should_create_rest_task_model_from_rest_task_vm() {
        Clock.freeze();
        RestTaskVM restTaskVM = RestTaskVmBuilder.aRestTaskVm()
                .id(UUID.randomUUID().toString())
                .body("body").expectedStatus(200)
                .group("group")
                .when(Clock.now())
                .method("GET")
                .name("name")
                .url("http://url")
                .addHeader("foo", "bar")
                .build();

        RestJobModel restJobModel = restTaskVM.toRestTaskJobModel();

        assertThat(restJobModel.getId()).isEqualTo(restTaskVM.getId());
        assertThat(restJobModel.getBody()).isEqualTo("body");
        assertThat(restJobModel.getExpectedStatus()).isEqualTo(200);
        assertThat(restJobModel.getHeaders()).isEqualTo(restTaskVM.getHeaders());
        assertThat(restJobModel.getMethod()).isEqualTo("GET");
        assertThat(restJobModel.getGroup()).isEqualTo("group");
        assertThat(restJobModel.getName()).isEqualTo("name");
        assertThat(restJobModel.getUrl()).isEqualTo("http://url");
        assertThat(restJobModel.getScheduleInfo()).isInstanceOf(TaskScheduler.class);
        assertThat(((TaskScheduler) restJobModel.getScheduleInfo()).getWhen()).isEqualTo(Clock.now());

        Clock.unfreeze();
    }
}