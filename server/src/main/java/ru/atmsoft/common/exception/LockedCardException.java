package ru.atmsoft.common.exception;

public class LockedCardException extends CommonException {

    public LockedCardException() {
    }

    public LockedCardException(String message) {
        super(message);
    }

    public LockedCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockedCardException(Throwable cause) {
        super(cause);
    }

    public LockedCardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
