package ru.sberbank.javascool.atm;

import lombok.Getter;
import ru.sberbank.javascool.atm.devices.Devices;
import ru.sberbank.javascool.atm.operation.*;
import ru.sberbank.javascool.card.TypeOfService;
import ru.sberbank.javascool.atm.devices.reader.CardReader;
import ru.sberbank.javascool.atm.devices.reader.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

public class ATMCard implements ATM {

    private List<Operation> operations = new ArrayList<>();
    private OperationLevel operationLevel = OperationLevel.Wait;
    @Getter
    private List<Reader> readers = Devices.getReaders();

    public ATMCard() {
        createReaders();
        createOperations();
    }

    /**
     * Создание картридеров, вынесено в отдельный метод, т.к. картридеры инициируют событие начала работы
     */
    private void createReaders() {
        BooleanSupplier isWait = () -> operationLevel == OperationLevel.Wait;
        Devices.getReaders().add(new CardReader(TypeOfService.Contact, isWait, this::cardReadEvent));
        Devices.getReaders().add(new CardReader(TypeOfService.Contactless, isWait, this::cardReadEvent));
    }

    /**
     * Создание списка доступных операций (с приходом спринга и ServiceLocator будет удалено)
     */
    private void createOperations() {
        operations.add(new OperationReadCard());
        operations.add(new OperationVerification());
        operations.add(new OperationQueryBalance());
        operations.add(new OperationExit());
    }

    /**
     * Запуск работы с текущем уровнем операции
     */
    private void run() {
        while (operationLevel != OperationLevel.End) {
            Optional<Operation> operation = selectOperation();
            operation.ifPresent(value -> operationLevel = value.run());
        }
        Devices.getDisplay().showMessage("Спасибо за использование нашего банкомата, ждём Вас в следующий раз");
        operationLevel = OperationLevel.Wait;
    }

    /**
     * Поиск всех возможных операций для текущего уровня
     * @return Выбранная операция
     */
    private Optional<Operation> selectOperation() {
        List<Operation> list = this.operations.stream().filter(o -> o.getActive(operationLevel)).collect(Collectors.toList());
        // Если нашли единственную подходящую операцию имеющую автозапуск, вернём её
        if (list.stream().anyMatch(Operation::getAutoRun))
            return list.stream().filter(Operation::getAutoRun).findFirst();
        Devices.getDisplay().showMessage("Выберете операцию из списка - введя номер и нажав enter");
        for (int i = 0; i < list.size(); i++)
            Devices.getDisplay().showMessage(String.format("%d. %s", i + 1, list.get(i).getName()));
        int num = Devices.getKeyBoard().getInt();
        if (num <= list.size())
            return Optional.of(list.get(num - 1));
        else
            return Optional.empty();
    }

    private void cardReadEvent(OperationLevel level){
        operationLevel = level;
        run();
    }

}
