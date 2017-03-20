package io.cronit.web;

import io.cronit.common.CronitBusinessException;
import io.cronit.web.vm.ParameterizedErrorVM;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(CronitBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ParameterizedErrorVM processParameterizedValidationError(CronitBusinessException ex) {
        return new ParameterizedErrorVM(ex.getErrorCode(), ex.getArgs());
    }

}
