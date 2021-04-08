package ru.sberbank.javascool.atm.devices.reader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.sberbank.javascool.atm.operation.OperationLevel;
import ru.sberbank.javascool.card.Card;
import ru.sberbank.javascool.card.TypeOfService;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * Ридер с возможностью чтиения карт через технологию NFC
 */
@Getter
@RequiredArgsConstructor
public class CardReader implements Reader {

    /**
     * Тип обслуживания для данного картридера
     */
    private final TypeOfService typeOfService;
    private final BooleanSupplier isWait;
    private final Consumer<OperationLevel> readEvent;
    private Card card;

    @Override
    public void insert(Card card) {
        // Если банкомат не в режиме ожидания, то игнорируем новые попытки вставить карту
        if (!isWait.getAsBoolean())
            return;
        // Если картридер поддерживает режим работы карты, то начинаем обработку
        if (card.getServiceSet().contains(getTypeOfService())) {
            this.card = card;
            readEvent.accept(OperationLevel.Read);
        } else
            readEvent.accept(OperationLevel.End);
    }

    @Override
    public String toString() {
        String message = "";
        switch (typeOfService) {
            case Contact:
                message = "Картридер принимающий пластиковые карты";
                break;
            case Contactless:
                message = "Бесконтактный картридер поддерживающий работу с NFC";
                break;
        }
        return message;
    }

    @Override
    public void returnCard() {
        this.card = null;
    }

}
