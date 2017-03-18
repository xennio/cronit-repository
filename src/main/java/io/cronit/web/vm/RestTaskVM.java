package io.cronit.web.vm;

import io.cronit.domain.RestJobModel;
import io.cronit.domain.TaskScheduler;

import java.time.ZonedDateTime;

public class RestTaskVM extends RestVM {
    private ZonedDateTime when;

    public ZonedDateTime getWhen() {
        return when;
    }

    public void setWhen(ZonedDateTime when) {
        this.when = when;
    }

    public RestJobModel toRestTaskJobModel() {
        RestJobModel restJobModel = toRestJobModel();
        TaskScheduler scheduleInfo = new TaskScheduler();
        scheduleInfo.setWhen(when);
        restJobModel.setScheduleInfo(scheduleInfo);
        return restJobModel;
    }
}
