package ru.sberbank.javascool.atm.devices.display;

import static java.lang.System.out;

/**
 * Дисплей
 */
public class DisplayConsole implements Display {

    @Override
    public void showError(String error){
        out.println(error);
    }

    @Override
    public void showMessage(String message) {
        out.println(message);
    }

}
