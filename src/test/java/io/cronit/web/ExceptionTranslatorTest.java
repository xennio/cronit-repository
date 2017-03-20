package io.cronit.web;

import io.cronit.common.CronitBusinessException;
import io.cronit.web.vm.ParameterizedErrorVM;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ExceptionTranslatorTest {

    @Test
    public void it_should_create_parameterized_error_view_model_in_cronit_business_exception() {
        ExceptionTranslator exceptionTranslator = new ExceptionTranslator();
        ParameterizedErrorVM parameterizedErrorVM = exceptionTranslator.
                processParameterizedValidationError(new CronitBusinessException("errorcode", "foo", "bar"));

        assertThat(parameterizedErrorVM.getErrorCode()).isEqualTo("errorcode");
        assertThat(parameterizedErrorVM.getArgs()[0]).isEqualTo("foo");
        assertThat(parameterizedErrorVM.getArgs()[1]).isEqualTo("bar");
    }

}