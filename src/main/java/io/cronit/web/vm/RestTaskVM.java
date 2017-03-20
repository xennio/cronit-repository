package io.cronit.web.vm;

import io.cronit.domain.RestJobModel;
import io.cronit.domain.TaskScheduler;
import org.joda.time.DateTime;

public class RestTaskVM extends RestVM {
    private DateTime when;

    public DateTime getWhen() {
        return when;
    }

    public void setWhen(DateTime when) {
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
