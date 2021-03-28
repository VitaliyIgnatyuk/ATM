package ru.sberbank.javascool.card;

/**
 * Банковская карта
 */
public interface BankCard extends Card {

    /**
     * Получение пин-кода банковской карты
     * @return пин-код
     */
    String getPinCode();

}
