package io.cronit.service;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HashService {

    @Value("${application.hash-salt}")
    private String salt;

    public String toMd5(String toHash) {
        String prepared = toHash + salt;
        return DigestUtils.md5Hex(prepared);
    }
}
