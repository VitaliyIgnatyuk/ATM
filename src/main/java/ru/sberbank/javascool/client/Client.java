package ru.sberbank.javascool.client;

import lombok.Getter;
import lombok.NonNull;
import ru.sberbank.javascool.account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Client {

    private static int count;
    @Getter
    private final Person person;
    @Getter
    private final int number;

    private List<Account<?>> accounts;

    /**
     * Клиент создаётся через статический метод, что бы счётчик клиентов увеличивался только в случае удачного создания объекта
     */
    public static Client createClient(@NonNull Person person) {
        Client client = new Client(person, count + 1);
        count++;
        return client;
    }

    public Optional<Account<?>> findAccount(String cardSerialNumber) {
        return accounts.stream().filter(a -> a.findCard(cardSerialNumber).isPresent()).findFirst();
    }

    public void addAccount(Account<?> account) {
        accounts.add(account);
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
