package ru.sberbank.javascool.atm;

import ru.sberbank.javascool.camera.Camera;
import ru.sberbank.javascool.camera.CameraImpl;
import ru.sberbank.javascool.display.Display;
import ru.sberbank.javascool.display.DisplayConsole;
import ru.sberbank.javascool.gateway.Gateway;
import ru.sberbank.javascool.gateway.GatewayImpl;
import ru.sberbank.javascool.keyboard.KeyBoard;
import ru.sberbank.javascool.keyboard.KeyBoardConsole;
import ru.sberbank.javascool.reader.Reader;
import ru.sberbank.javascool.scaner.Scaner;
import ru.sberbank.javascool.scaner.ScanerImpl;
import ru.sberbank.javascool.speaker.Speaker;
import ru.sberbank.javascool.speaker.SpeakerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * В будующем будет заменён на ServiceLocator
 */
public class Devices {

    private static Display display = new DisplayConsole();
    private static KeyBoard keyBoard = new KeyBoardConsole();
    private static BillAcceptor billAcceptor = new BillAcceptor();
    private static BillIssue billIssue = new BillIssue();
    private static Gateway gateway = new GatewayImpl();
    private static Speaker speaker = new SpeakerImpl();
    private static Camera camera = new CameraImpl();
    private static Scaner scaner = new ScanerImpl();
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

    public static BillAcceptor getBillAcceptor() {
        return billAcceptor;
    }

    public static BillIssue getBillIssue() {
        return billIssue;
    }

    public static Speaker getSpeaker() {
        return speaker;
    }

    public static Camera getCamera() {
        return camera;
    }

    public static Scaner getScaner() {
        return scaner;
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
