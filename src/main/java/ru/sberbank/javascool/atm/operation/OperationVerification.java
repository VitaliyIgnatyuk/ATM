package ru.sberbank.javascool.atm.operation;

import lombok.Getter;
import ru.sberbank.javascool.atm.ATMException;
import ru.sberbank.javascool.atm.Devices;
import ru.sberbank.javascool.card.BankCard;

/**
 * Операция проверки пинкода
 */
@Getter
public class OperationVerification implements Operation {

    private final String name = "Операция проверки подлинности";

    @Override
    public OperationLevel run() {
        int max = 3;
        for (int i = 0; i < max; i++) {
            Devices.getDisplay().showMessage("Введите пинкод (или введите exit для выхода): ");
            String pinCode = Devices.getKeyBoard().getData();
            if (pinCode.equals("exit"))
                return OperationLevel.Exit;
            if (!(Devices.getActiveReader().getCard() instanceof BankCard))
                throw new ATMException("Вставленная карта не является банковской");
            BankCard bankCard = (BankCard)Devices.getActiveReader().getCard();
            if (bankCard.getPinCode().equals(pinCode)) {
                Devices.getDisplay().showMessage("Авторизация пройдена!");
                return OperationLevel.Authorised;
            }
            else
                Devices.getDisplay().showMessage(String.format("Вы ввели не верный пинкод (осталось попыток: %s)", max - i - 1));
        }
        Devices.getDisplay().showError("Все попытки ввода пинкода исчерпаны");
        return OperationLevel.Exit;
    }

    @Override
    public boolean getActive(OperationLevel operationLevel) {
        return operationLevel == OperationLevel.NonAuthorised;
    }

    @Override
    public boolean getAutoRun() {
        return true;
    }

}