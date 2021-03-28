package ru.sberbank.javascool.atm.operation;

import lombok.Getter;
import ru.sberbank.javascool.atm.Devices;
import ru.sberbank.javascool.reader.Reader;

import java.util.Optional;

@Getter
public class OperationExit implements Operation {

    private final String name = "Выход";

    @Override
    public OperationLevel run() {
        Optional<Reader> reader = Devices.getReaders().stream().filter(Reader::active).findFirst();
        if (reader.isPresent()) {
            reader.get().returnCard();
            Devices.getDisplay().showMessage("Не забудьте взять вашу карту");
        }
        return OperationLevel.End;
    }

    @Override
    public boolean getActive(OperationLevel operationLevel) {
        return true;
    }

}
