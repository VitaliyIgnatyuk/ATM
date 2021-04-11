package ru.sberbank.javascool.atm.devices.reader;

import ru.sberbank.javascool.card.Card;

/**
 * Устройство для чтения карт
 */
public interface Reader {

    /**
     * Вставленная карта
     */
    Card getCard();

    /**
     * Активное устройство
     */
    default boolean active() {
        return getCard() != null;
    }

    /**
     * Чтение карты
     *
     * @param card Карта
     */
    void insert(Card card);

    /**
     * Возврат карты и окончание работы
     */
    void returnCard();
}
