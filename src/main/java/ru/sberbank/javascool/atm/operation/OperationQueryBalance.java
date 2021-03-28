package ru.sberbank.javascool.atm.operation;

import lombok.Getter;
import ru.sberbank.javascool.atm.Devices;

/**
 * Операция запроса баланса
 */
@Getter
public class OperationQueryBalance implements Operation {

    private final String name = "Запрос баланса";

    @Override
    public OperationLevel run() {
        Devices.getDisplay().showMessage("Происходит запрос баланса, пожалуйста подождите...");
        Double balance = Devices.getGateway().getBalance(Devices.getActiveReader().getCard().getSerialNumber());
        Devices.getDisplay().showMessage(String.format("Ваш баланс: %f", balance));
        return OperationLevel.Authorised;
    }

    @Override
    public boolean getActive(OperationLevel operationLevel) {
        return operationLevel == OperationLevel.Authorised;
    }

}
