package ru.sberbank.javascool.examplejdbc;

public class JDBCConnectionException extends RuntimeException {

    public JDBCConnectionException() {
    }

    public JDBCConnectionException(String message) {
        super(message);
    }

    public JDBCConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JDBCConnectionException(Throwable cause) {
        super(cause);
    }

    public JDBCConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
