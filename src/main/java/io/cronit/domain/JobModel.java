package io.cronit.domain;

import com.couchbase.client.java.repository.annotation.Id;

public abstract class JobModel  {
    @Id
    private String id;
    private String name;
    private String group;
    private ScheduleInfo scheduleInfo;

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setScheduleInfo(ScheduleInfo scheduleInfo) {
        this.scheduleInfo = scheduleInfo;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public ScheduleInfo getScheduleInfo() {
        return scheduleInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
