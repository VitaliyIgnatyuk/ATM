package ru.sberbank.javascool.client;

import lombok.Getter;
import ru.sberbank.javascool.account.Account;

import java.util.ArrayList;
import java.util.List;


public class Client {

    private static int count;
    @Getter
    private final Person person;
    @Getter
    private final int number;
    @Getter
    private List<Account<?>> accounts;

    /**
     * Клиент создаётся через статический метод, что бы счётчик клиентов увеличивался только в случае удачного создания объекта
     */
    public static Client createClient(Person person) {
        Client client = new Client(person, count + 1);
        count++;
        return client;
    }

    /**
     * Скрываем конструктор
     */
    private Client(Person person, int number){
        this.person = person;
        this.number = number;
        accounts = new ArrayList<>();
    }

}
