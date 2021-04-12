package ru.sberbank.javascool;

import ru.sberbank.javascool.atm.ATM;
import ru.sberbank.javascool.atm.ATMCard;
import ru.sberbank.javascool.atm.devices.reader.Reader;
import ru.sberbank.javascool.card.Card;
import ru.sberbank.javascool.server.Server;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.out;


public class Program {

    public static void main(String[] args) {
        checkBalance();
    }

    private static void checkBalance() {
        Server server = new Server();
        out.println(server);
        Optional<Card> card = select(new Purse().getCards(), "карту");
        //card.orElseThrow()
        if (!card.isPresent())
            return;
        out.println("Выбрана карта " + card.get().toString());

        ATM atm = new ATMCard();
        Optional<Reader> reader = select(atm.getReaders(), "картридер");
        if (!reader.isPresent())
            return;
        out.println("Выбран " + reader.get().toString());
        reader.get().insert(card.get());
    }

    private static <T> Optional<T> select(List<T> list, String name) {
        Scanner console = new Scanner(System.in);
        out.printf("Выберете %s из списка - введя номер и нажав enter (0 для выхода)\n", name);
        for (int i = 0; i < list.size(); i++)
            out.printf("%d. %s\n", i + 1, list.get(i).toString());
        int num = Integer.parseInt(console.nextLine());
        if (num <= list.size())
            return Optional.of(list.get(num - 1));
        else
            return Optional.empty();
    }

}
