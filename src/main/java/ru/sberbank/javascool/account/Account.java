package ru.sberbank.javascool.account;

import lombok.Getter;
import lombok.NonNull;
import ru.sberbank.javascool.card.Card;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Account<T extends Balance> {

    @Getter
    private final String account;
    @Getter
    private final T balance;

    /**
     * Номер карты с ключём "Номер карты"
     */
    private Map<String, Card> cards = new HashMap<>();

    public Account(String account, @NonNull T balance) {
        this.account = account;
        this.balance = balance;
    }

    public void addCard(@NonNull Card card) {
        cards.put(card.getSerialNumber(), card);
    }

    public Optional<Card> findCard(String serialNumber) {
        return Optional.ofNullable(cards.get(serialNumber));
    }

}
