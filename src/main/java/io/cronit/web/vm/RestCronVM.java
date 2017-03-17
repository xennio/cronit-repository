package io.cronit.web.vm;

import io.cronit.domain.CronScheduler;
import io.cronit.domain.RestJobModel;

import java.util.HashMap;
import java.util.Map;

public class RestCronVM {

    private String id;
    private String name;
    private String group;
    private String method;
    private String url;
    private Map<String, String> headers = new HashMap<>();
    private String body;
    private int expectedStatus;
    private String expression;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getExpectedStatus() {
        return expectedStatus;
    }

    public void setExpectedStatus(int expectedStatus) {
        this.expectedStatus = expectedStatus;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public RestJobModel toRestJobModel() {
        RestJobModel restJobModel = new RestJobModel();
        restJobModel.setBody(body);
        restJobModel.setExpectedStatus(expectedStatus);
        restJobModel.setHeaders(headers);
        restJobModel.setUrl(url);
        restJobModel.setId(id);
        restJobModel.setMethod(method);
        restJobModel.setGroup(group);
        restJobModel.setName(name);
        CronScheduler scheduleInfo = new CronScheduler();
        scheduleInfo.setExpression(expression);
        restJobModel.setScheduleInfo(scheduleInfo);
        return restJobModel;
    }
}
