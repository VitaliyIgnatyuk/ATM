package ru.sberbank.javascool.atm.devices.keyboard;

import java.util.Scanner;

public class KeyBoardConsole implements KeyBoard {

    private Scanner scanner = new Scanner(System.in);

    public String getData() {
        return scanner.nextLine();
    }

    public int getInt() {
        return Integer.parseInt(scanner.nextLine());
    }
}
