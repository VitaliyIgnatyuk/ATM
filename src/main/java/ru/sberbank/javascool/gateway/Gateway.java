package ru.sberbank.javascool.gateway;

/**
 * Шлюз для работы с банковским сервером
 */
public interface Gateway {
    /**
     * Проверка связи с банком
     */
    boolean testLink();

    double getBalance(String serialNumber);

}
