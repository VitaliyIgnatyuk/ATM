package ru.sberbank.javascool.atm.operation;

import lombok.Getter;
import ru.sberbank.javascool.atm.devices.Devices;

import java.math.BigDecimal;

/**
 * Операция запроса баланса
 */
@Getter
public class OperationQueryBalance implements Operation {

    private final String name = "Запрос баланса";

    @Override
    public OperationLevel run() {
        Devices.getDisplay().showMessage("Происходит запрос баланса, пожалуйста подождите...");
        BigDecimal balance = Devices.getGateway().getBalance(Devices.getActiveReader().getCard().getSerialNumber());
        Devices.getDisplay().showMessage(String.format("Ваш баланс: %s", balance.toString()));
        return OperationLevel.Authorised;
    }

    @Override
    public boolean getActive(OperationLevel operationLevel) {
        return operationLevel == OperationLevel.Authorised;
    }

}
