package ru.sberbank.javascool.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Базовый класс всех для всех типов карт
 */
@Getter
@AllArgsConstructor
public class CardImpl implements Card{

    /**
     * Название карты
     */
    private String name;

    /**
     * Серийный номер
     */
    private String serialNumber;
    /**
     * Типы обслуживания для данной карты
     */
    private Set<TypeOfService> serviceSet;

    @Override
    public String toString() {
        return name;
    }

}
