package ru.sberbank.javascool.card;

import java.util.Set;

/**
 * Карточка, пластиковая или электронная
 */
public interface Card {

    /**
     * @return Название карты
     */
    String getName();

    /**
     * Возвращает серийный номер карты
     * @return серийный номер карты
     */
    String getSerialNumber();

    /**
     * Возвращает множество возможных вариантов работы с текущей картой
     * @return контактно, бесконтактно
     */
    Set<TypeOfService> getServiceSet();

    /**
     * Получение пин-кода банковской карты
     * @return пин-код
     */
    String getPinCode();

}
