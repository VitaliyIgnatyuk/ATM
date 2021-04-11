package ru.sberbank.javascool.atm.devices;

import ru.sberbank.javascool.atm.ATMException;
import ru.sberbank.javascool.atm.devices.display.Display;
import ru.sberbank.javascool.atm.devices.display.DisplayConsole;
import ru.sberbank.javascool.atm.devices.gateway.Gateway;
import ru.sberbank.javascool.atm.devices.gateway.GatewayHttp;
import ru.sberbank.javascool.atm.devices.keyboard.KeyBoard;
import ru.sberbank.javascool.atm.devices.keyboard.KeyBoardConsole;
import ru.sberbank.javascool.atm.devices.reader.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * В будующем будет заменён на ServiceLocator
 */
public class Devices {

    private static Display display = new DisplayConsole();
    private static KeyBoard keyBoard = new KeyBoardConsole();
    private static Gateway gateway = new GatewayHttp();
    private static List<Reader> readers = new ArrayList<>();

    public static List<Reader> getReaders() {
        return readers;
    }

    public static Display getDisplay() {
        return display;
    }

    public static KeyBoard getKeyBoard() {
        return keyBoard;
    }

    public static Gateway getGateway() {
        return gateway;
    }

    public static Reader getActiveReader() {
        Optional<Reader> reader = readers.stream().filter(Reader::active).findFirst();
        if (!reader.isPresent())
            throw new ATMException("Не найден активный картридер");
        return reader.get();
    }

}
