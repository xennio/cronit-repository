package io.cronit.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HashServiceTest {

    @Test
    public void it_should_hash_with_salt_value() {
        HashService hashService = new HashService();

        String toHash = "toHash";
        String salt = "salt";

        String hashed = hashService.toMd5(toHash, salt);

        assertThat(hashed).isEqualTo("67371bf6fbbb6fe7321e23fedf834268");
    }
}