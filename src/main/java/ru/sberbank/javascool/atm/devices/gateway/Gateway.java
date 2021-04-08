package ru.sberbank.javascool.atm.devices.gateway;

import java.math.BigDecimal;

/**
 * Шлюз для работы с банковским сервером
 */
public interface Gateway {
    /**
     * Проверка связи с банком
     */
    boolean testLink();

    BigDecimal getBalance(String serialNumber);

}
