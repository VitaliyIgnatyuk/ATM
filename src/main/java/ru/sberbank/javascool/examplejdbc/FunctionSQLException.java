package ru.sberbank.javascool.examplejdbc;

import java.sql.SQLException;

@FunctionalInterface
public interface FunctionSQLException<T, R> {

    R apply(T t) throws SQLException;

}
