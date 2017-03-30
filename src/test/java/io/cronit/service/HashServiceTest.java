package io.cronit.service;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class HashServiceTest {

    @Test
    public void it_should_hash_with_salt_value() {
        HashService hashService = new HashService();
        ReflectionTestUtils.setField(hashService, "salt", "salt");
        String toHash = "toHash";
        String hashed = hashService.toMd5(toHash);

        assertThat(hashed).isEqualTo("67371bf6fbbb6fe7321e23fedf834268");
    }
}