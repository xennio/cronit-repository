package io.cronit.builder;

import io.cronit.web.vm.RestTaskVM;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class RestTaskVmBuilder {
    private DateTime when;
    private String id;
    private String name;
    private String group;
    private String method;
    private String url;
    private Map<String, String> headers = new HashMap<>();
    private String body;
    private int expectedStatus;

    private RestTaskVmBuilder() {
    }

    public static RestTaskVmBuilder aRestTaskVm() {
        return new RestTaskVmBuilder();
    }

    public RestTaskVmBuilder when(DateTime when) {
        this.when = when;
        return this;
    }

    public RestTaskVmBuilder id(String id) {
        this.id = id;
        return this;
    }

    public RestTaskVmBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RestTaskVmBuilder group(String group) {
        this.group = group;
        return this;
    }

    public RestTaskVmBuilder method(String method) {
        this.method = method;
        return this;
    }

    public RestTaskVmBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RestTaskVmBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public RestTaskVmBuilder body(String body) {
        this.body = body;
        return this;
    }

    public RestTaskVmBuilder expectedStatus(int expectedStatus) {
        this.expectedStatus = expectedStatus;
        return this;
    }

    public RestTaskVmBuilder addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public RestTaskVM build() {
        RestTaskVM restTaskVm = new RestTaskVM();
        restTaskVm.setWhen(when);
        restTaskVm.setId(id);
        restTaskVm.setName(name);
        restTaskVm.setGroup(group);
        restTaskVm.setMethod(method);
        restTaskVm.setUrl(url);
        restTaskVm.setHeaders(headers);
        restTaskVm.setBody(body);
        restTaskVm.setExpectedStatus(expectedStatus);
        return restTaskVm;
    }

    public static RestTaskVM aSampleTaskVM(DateTime when) {
        return RestTaskVmBuilder.aRestTaskVm()
                .id(UUID.randomUUID().toString())
                .body("body").expectedStatus(200)
                .group("group")
                .when(when)
                .method("GET")
                .name("SampleJob")
                .url("http://url")
                .addHeader("foo", "bar")
                .addHeader("key", "val")
                .build();
    }
}
