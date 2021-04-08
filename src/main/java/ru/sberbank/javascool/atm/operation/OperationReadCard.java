package ru.sberbank.javascool.atm.operation;

import lombok.Getter;
import ru.sberbank.javascool.atm.devices.Devices;
import ru.sberbank.javascool.card.BankCard;

@Getter
public class OperationReadCard implements Operation {

    private final String name = "Чтение карты";

    @Override
    public OperationLevel run() {
        if (Devices.getActiveReader().getCard() instanceof BankCard)
            return OperationLevel.NonAuthorised;
        Devices.getDisplay().showError("Невозможно прочитать карту");
        return OperationLevel.End;
    }

    @Override
    public boolean getActive(OperationLevel operationLevel) {
        return operationLevel == OperationLevel.Read;
    }

    @Override
    public boolean getAutoRun() {
        return true;
    }

}
