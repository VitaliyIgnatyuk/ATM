package ru.sberbank.javascool.card;

import lombok.Getter;

import java.util.Set;

/**
 * Банковская карта
 */
@Getter
public class BankCardImpl extends CardImpl implements BankCard {

    /**
     * Получение пин-кода банковской карты
     */
    private String pinCode;

    public BankCardImpl(String name, String serialNumber, Set<TypeOfService> serviceSet, String pinCode) {
        super(name, serialNumber, serviceSet);
        this.pinCode = pinCode;
    }

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
        String result = super.toString();
        result += !services.isEmpty() ? String.format(" поддерживает способы оплаты: %s", services) : "";
        return result;
    }
}
