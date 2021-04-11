package ru.sberbank.javascool.atm.devices.display;

/**
 * Дисплей
 */
public interface Display {

    void showError(String error);

    void showMessage(String message);

}
