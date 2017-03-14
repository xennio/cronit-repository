package io.cronit.builder;

import io.cronit.domain.JobModel;
import io.cronit.domain.RestJobModel;
import io.cronit.domain.ScheduleInfo;

public class RestJobModelBuilder {

    RestJobModel restJobModel = new RestJobModel();

    public RestJobModelBuilder name(String name) {
        restJobModel.setName(name);
        return this;
    }

    public RestJobModelBuilder group(String group) {
        restJobModel.setGroup(group);
        return this;
    }

    public RestJobModelBuilder scheduleInfo(ScheduleInfo scheduleInfo) {
        restJobModel.setScheduleInfo(scheduleInfo);
        return this;
    }

    public RestJobModelBuilder addHeader(String key, String value) {
        restJobModel.getHeaders().put(key, value);
        return this;
    }


    public RestJobModelBuilder url(String url) {
        restJobModel.setUrl(url);
        return this;
    }

    public RestJobModelBuilder method(String method) {
        restJobModel.setMethod(method);
        return this;
    }

    public RestJobModelBuilder body(String body) {
        restJobModel.setBody(body);
        return this;
    }

    public RestJobModelBuilder expectedStatus(int statusCode) {
        restJobModel.setExpectedStatus(statusCode);
        return this;
    }

    public JobModel build() {
        return restJobModel;
    }

}
