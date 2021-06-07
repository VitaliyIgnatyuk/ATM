package ru.atmsoft.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ErrorCode {
    NonValidSession("Время сессии истекло"),
    UnknownError("Произошла неизвестная ошибка"),
    CardLocked("Карта заблокирована"),
    NotValidCard("Истёк срок действия карты"),
    FatalError("Произошла ошибка, попробуйте заново"),
    CardIsNotReadable("Невозможно прочитать карту"),
    NotFound("Информация недоступна"),
    BadRequest("Данные не корректны");

    private String errorMessage;

}
