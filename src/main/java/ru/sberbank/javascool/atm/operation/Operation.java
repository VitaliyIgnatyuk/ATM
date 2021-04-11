package ru.sberbank.javascool.atm.operation;

/**
 * Операция
 */
public interface Operation {

    String getName();
    /**
     * Запуск выполнения операции
     * @return уровень операции после выполнения текущей операции
     */
    OperationLevel run();
    /**
     * Активна ли операция при текущум уровне
     */
    boolean getActive(OperationLevel operationLevel);

    /**
     * Автозапуск операции
     */
    default boolean getAutoRun() {
        return false;
    }

}
