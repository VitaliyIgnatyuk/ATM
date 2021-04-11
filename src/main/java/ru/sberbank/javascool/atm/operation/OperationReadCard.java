package ru.sberbank.javascool.atm.operation;

import lombok.Getter;

@Getter
public class OperationReadCard implements Operation {

    private final String name = "Чтение карты";

    @Override
    public OperationLevel run() {
        return OperationLevel.NonAuthorised;
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
