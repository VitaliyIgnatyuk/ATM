package ru.sberbank.javascool.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

/**
 * Банковская карта
 */
@Getter
@AllArgsConstructor
public class BankCard implements Card {

    /**
     * Название карты
     */
    private String name;

    /**
     * Серийный номер
     */
    private String serialNumber;
    /**
     * Типы обслуживания для данной карты
     */
    private Set<TypeOfService> serviceSet;

    /**
     * Получение пин-кода банковской карты
     */
    private String pinCode;

    /**
     * Дата истечения срока действия
     */
    private LocalDate expiredDate;

    @Override
    public String toString() {
        String services = "";

        for (TypeOfService tos : getServiceSet()) {
            switch (tos) {
                case Contact:
                    services = services.isEmpty() ? "контактный" : String.format("%s, %s", services, "контактный");
                    break;
                case Contactless:
                    services = services.isEmpty() ? "бесконтактный" : String.format("%s, %s", services, "бесконтактный");
                    break;
            }
        }
        String result = name;
        result += !services.isEmpty() ? String.format(" поддерживает способы оплаты: %s", services) : "";
        return result;
    }
}
