package ru.sberbank.javascool.account;

import lombok.Getter;
import ru.sberbank.javascool.card.Card;

import java.util.HashMap;
import java.util.Optional;

@Getter
public class Account<T extends Balance> {

    private final String account;

    private final T balance;

    /**
     * Номер карты с ключём "Номер карты"
     */
    private HashMap<String, Card> cards = new HashMap<>();

    public Account(String account, T balance) {
        this.account = account;
        this.balance = balance;
    }

    public Optional<Card> findCard(String serialNumber) {
        return Optional.ofNullable(cards.get(serialNumber));
    }

}
