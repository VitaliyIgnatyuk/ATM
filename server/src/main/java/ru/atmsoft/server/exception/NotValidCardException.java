package ru.atmsoft.server.exception;

import ru.atmsoft.common.exception.CommonException;

public class NotValidCardException extends CommonException {

    public NotValidCardException() {
    }

    public NotValidCardException(String message) {
        super(message);
    }

    public NotValidCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidCardException(Throwable cause) {
        super(cause);
    }

    public NotValidCardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
