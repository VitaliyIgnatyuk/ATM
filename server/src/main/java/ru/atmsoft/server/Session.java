package ru.atmsoft.server;

import java.util.UUID;

/**
 * Сессия для одного устройства
 */
public interface Session {

    /** Идентификатор устройства */
    String getDeviceId();

    /** Идентификатор сессии */
    String getSessionId();

    /** Идентификатор карты */
    UUID getCardId();

    /** Метод для обновления времени жизни сессии */
    Session updateSession();

    /** Получение информации об текущей активности сессии */
    boolean getActive();

}
