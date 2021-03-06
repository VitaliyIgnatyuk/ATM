package ru.sberbank.javascool.atm;

import ru.sberbank.javascool.atm.devices.reader.Reader;

import java.util.List;

/**
 * Банкомат
 */
public interface ATM {

    /**
     * Список устройств для ввода карты
     */
    List<Reader> getReaders();

}
