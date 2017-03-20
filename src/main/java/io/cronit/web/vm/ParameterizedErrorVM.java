package io.cronit.web.vm;

public class ParameterizedErrorVM {
    private final String errorCode;
    private final Object[] args;

    public ParameterizedErrorVM(String errorCode, Object... args) {

        this.errorCode = errorCode;
        this.args = args;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
