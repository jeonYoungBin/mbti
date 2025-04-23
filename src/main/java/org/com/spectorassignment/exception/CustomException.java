package org.com.spectorassignment.exception;

public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String formattedMessage;

    public CustomException(ErrorCode errorCode, Object... args) {
        super(String.format(errorCode.getMessage(), args));
        this.errorCode = errorCode;
        this.formattedMessage = String.format(errorCode.getMessage(), args);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getFormattedMessage() {
        return formattedMessage;
    }
}
