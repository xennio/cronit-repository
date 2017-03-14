package io.cronit.service;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class HashService {
    public String toMd5(String toHash, String salt) {
        String prepared = toHash + salt;
        return DigestUtils.md5Hex(prepared);
    }
}
