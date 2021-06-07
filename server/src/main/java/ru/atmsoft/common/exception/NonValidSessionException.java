package ru.atmsoft.common.exception;

public class NonValidSessionException extends CommonException {

    public NonValidSessionException() {
    }

    public NonValidSessionException(String message) {
        super(message);
    }

    public NonValidSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonValidSessionException(Throwable cause) {
        super(cause);
    }

    public NonValidSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
