package ru.sberbank.javascool.examplejdbc;

import java.sql.*;
import java.util.Optional;

public class ExampleJDBC {

    private String url;

    public ExampleJDBC(String url) {
        this.url = url;
    }

    public Optional<String> selectColumn() throws SQLException {
        return select("select name from machine", rs -> rs.getString("NAME"));
    }

    public Optional<String> selectTwoColumns() throws SQLException {
        return select("select name, age from animal", rs -> String.format("Name: %s, age: %d", rs.getString("NAME"), rs.getInt("age")));
    }

    private Optional<Connection> getConnection() throws SQLException {
        return Optional.ofNullable(DriverManager.getConnection(url));
    }

    private Optional<String> select(String sql, FunctionSQLException<ResultSet, String> func) throws SQLException {
        try (ResultSet resultSet = getConnection().orElseThrow(JDBCConnectionException::new).createStatement().executeQuery(sql)) {
            return resultSet.next() ? Optional.of(func.apply(resultSet)) : Optional.empty();
        }
    }

}
