package ru.atmsoft.common.exception;

public class CardIsNotReadableException extends CommonException {

    public CardIsNotReadableException() {
    }

    public CardIsNotReadableException(String message) {
        super(message);
    }

    public CardIsNotReadableException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardIsNotReadableException(Throwable cause) {
        super(cause);
    }

    public CardIsNotReadableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
