package io.cronit.common;

public class CronitSystemException extends RuntimeException {
    private String errorCode;
    private Object[] args;

    public CronitSystemException(String errorCode, Object... args) {
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
