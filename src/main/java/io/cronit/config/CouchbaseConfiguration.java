package io.cronit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "couchbase")
@EnableCouchbaseRepositories
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    private final Logger log = LoggerFactory.getLogger(CouchbaseConfiguration.class);

    private List<String> hosts = new ArrayList<>();

    private String bucket;

    private String password;

    @Override
    protected List<String> getBootstrapHosts() {
        return hosts;
    }

    @Override
    protected String getBucketName() {
        return bucket;
    }

    @Override
    protected String getBucketPassword() {
        return password;
    }

    @PostConstruct
    public void afterInit() {
        log.info("Couchbase configuration setup is ready. Connected hosts: " + String.join(",", hosts) + " bucket:" + bucket);
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getHosts() {
        return hosts;
    }
}
