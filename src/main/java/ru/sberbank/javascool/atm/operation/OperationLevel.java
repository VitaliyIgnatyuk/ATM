package ru.sberbank.javascool.atm.operation;

/**
 * Уровни операций (для определения доступных операций)
 */
public enum OperationLevel {
    Wait,
    Read,
    NonAuthorised,
    Authorised,
    Exit,
    End
}
