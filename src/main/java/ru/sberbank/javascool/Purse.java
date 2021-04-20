package ru.sberbank.javascool;

import lombok.Getter;
import ru.sberbank.javascool.card.BankCard;
import ru.sberbank.javascool.card.Card;
import ru.sberbank.javascool.card.TypeOfService;

import java.time.LocalDate;
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
        addBankCard("Банковская карта СБЕРБАНК", "3333333", "0000",
                convert(TypeOfService.Contact, TypeOfService.Contactless), LocalDate.of(2022, 1, 15));
        addBankCard("Банковская карта АЛЬФАБАНК", "4444444", "0000",
                convert(TypeOfService.Contact), LocalDate.of(2021, 9, 3));
        addBankCard("Электронная банковская карта ТИНЬКОФФ", "5555555", "0000",
                convert(TypeOfService.Contactless), LocalDate.of(2020, 11, 7));
    }

    private Set<TypeOfService> convert(TypeOfService ... args) {
        return new HashSet<>(Arrays.asList(args));
    }

    private void addBankCard(String name, String serialNumber, String pinCode, Set<TypeOfService> typeOfServices, LocalDate expiredDate) {
        cards.add(new BankCard(name, serialNumber, typeOfServices, pinCode, expiredDate));
    }

}
