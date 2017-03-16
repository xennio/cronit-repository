package io.cronit.domain;

import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import io.cronit.common.CronitSystemException;

public class CronScheduler extends ScheduleInfo {

    private String expression;

    public CronScheduler() {
        super(ScheduleType.CRON);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public void validate() {
        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX));
        try {
            parser.parse(expression);
        } catch (IllegalArgumentException iae) {
            throw new CronitSystemException("expression.not.valid", expression);
        }
    }
}
