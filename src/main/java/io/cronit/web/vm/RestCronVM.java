package io.cronit.web.vm;

import io.cronit.domain.CronScheduler;
import io.cronit.domain.RestJobModel;

public class RestCronVM extends RestVM {

    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public RestJobModel toRestCronJobModel() {
        RestJobModel restJobModel = toRestJobModel();
        CronScheduler scheduleInfo = new CronScheduler();
        scheduleInfo.setExpression(expression);
        restJobModel.setScheduleInfo(scheduleInfo);
        return restJobModel;
    }
}
