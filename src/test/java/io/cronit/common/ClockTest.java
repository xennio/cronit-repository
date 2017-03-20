package io.cronit.common;


import io.cronit.utils.ClockUtils;
import org.junit.Test;
import org.springframework.data.util.ReflectionUtils;

import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ClockTest {

    @Test
    public void it_should_return_false_when_date_is_before_now() {
        Clock.freeze(ClockUtils.toLocalDate("20150320"));
        assertThat(Clock.isAfterNow(new Date())).isFalse();
        Clock.unfreeze();
    }

    @Test
    public void it_should_return_true_when_date_is_before_now() {
        Clock.freeze(ClockUtils.toLocalDate("20150320"));
        assertThat(Clock.isBeforeNow(new Date())).isTrue();
        Clock.unfreeze();
    }

    @Test
    public void it_should_not_initialize_externally() {
        assertThat(ReflectionUtils.findConstructor(Clock.class).isAccessible()).isFalse();
    }

}