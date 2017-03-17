package io.cronit.builder;

import io.cronit.web.vm.RestCronVM;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public final class RestCronVMBuilder {

    private String id;
    private String name;
    private String group;
    private String method;
    private String url;
    private Map<String, String> headers = new HashMap<>();
    private String body;
    private int expectedStatus;
    private String expression;

    private RestCronVMBuilder() {
    }

    public static RestCronVMBuilder aRestCronVM() {
        return new RestCronVMBuilder();
    }

    public RestCronVMBuilder id(String id) {
        this.id = id;
        return this;
    }

    public RestCronVMBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RestCronVMBuilder group(String group) {
        this.group = group;
        return this;
    }

    public RestCronVMBuilder method(String method) {
        this.method = method;
        return this;
    }

    public RestCronVMBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RestCronVMBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public RestCronVMBuilder body(String body) {
        this.body = body;
        return this;
    }

    public RestCronVMBuilder expectedStatus(int expectedStatus) {
        this.expectedStatus = expectedStatus;
        return this;
    }

    public RestCronVMBuilder expression(String expression) {
        this.expression = expression;
        return this;
    }

    public RestCronVMBuilder addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public RestCronVM build() {
        RestCronVM restCronVM = new RestCronVM();
        restCronVM.setId(id);
        restCronVM.setName(name);
        restCronVM.setGroup(group);
        restCronVM.setMethod(method);
        restCronVM.setUrl(url);
        restCronVM.setHeaders(headers);
        restCronVM.setBody(body);
        restCronVM.setExpectedStatus(expectedStatus);
        restCronVM.setExpression(expression);
        return restCronVM;
    }

    public static RestCronVM aSampleCronVM() {
        return RestCronVMBuilder.aRestCronVM()
                .id(UUID.randomUUID().toString())
                .body("body").expectedStatus(200)
                .group("group")
                .expression("* * * * *")
                .method("GET")
                .name("SampleJob")
                .url("http://url")
                .addHeader("foo", "bar")
                .addHeader("key", "val")
                .build();
    }
}
