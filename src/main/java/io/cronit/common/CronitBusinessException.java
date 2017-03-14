package io.cronit.common;

public class CronitBusinessException extends RuntimeException {
    private String errorCode;
    private Object[] args;

    public CronitBusinessException(String errorCode, Object... args) {
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
