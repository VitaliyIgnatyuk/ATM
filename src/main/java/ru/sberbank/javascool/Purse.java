package ru.sberbank.javascool;

import lombok.Getter;
import ru.sberbank.javascool.card.BankCardImpl;
import ru.sberbank.javascool.card.Card;
import ru.sberbank.javascool.card.CardImpl;
import ru.sberbank.javascool.card.TypeOfService;

import java.util.*;

/**
 * Портмоне содержащий различные карты
 */
@Getter
public class Purse {

    /**
     * Список карт содержащихся в портмоне
     */
    private List<Card> cards = new ArrayList<>();

    public Purse() {
        AddCard("Скидочная карта МАГНИТ", "11111111", convert(TypeOfService.Contact));
        AddCard("Бонусная карта ЛЕНТА", "2222222", convert(TypeOfService.Contact));

        AddBankCard("Банковская карта СБЕРБАНК", "3333333", "0000", convert(TypeOfService.Contact, TypeOfService.Contactless));
        AddBankCard("Банковская карта АЛЬФАБАНК", "4444444", "0000", convert(TypeOfService.Contact));
        AddBankCard("Электронная банковская карта ТИНЬКОФФ", "5555555", "0000", convert(TypeOfService.Contactless));
    }

    private Set<TypeOfService> convert(TypeOfService ... args) {
        return new HashSet<>(Arrays.asList(args));
    }

    private void AddCard(String name, String serialNumber, Set<TypeOfService> typeOfServices) {
        cards.add(new CardImpl(name, serialNumber, typeOfServices));
    }

    private void AddBankCard(String name, String serialNumber, String pinCode, Set<TypeOfService> typeOfServices) {
        cards.add(new BankCardImpl(name, serialNumber, typeOfServices, pinCode));
    }

}
