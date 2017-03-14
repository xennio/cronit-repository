package io.cronit.domain;

public class CronScheduler extends ScheduleInfo {
    private String expression;

    public CronScheduler() {
        super(ScheduleType.CRON);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
