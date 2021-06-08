package ru.atmsoft.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.EnumSet;

@Getter
@ToString
@AllArgsConstructor
public enum Currency {

    EUR("Euro",978),
    USD("US Dollar",840),
    CNY("Yuan Renminbi",156),
    RUB("Russian Ruble",643),
    GBP("Pound Sterling",826);

    private String name;
    private int codeISO;

    public static Currency getType(Integer codeISO) {
        if (codeISO == null) {
            return null;
        }
        // Возможно стоит поместить stream в переменную, что бы не вычислять его каждый раз???
        return EnumSet.allOf(Currency.class).stream().filter(cur -> codeISO.equals(cur.getCodeISO()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No matching type for id " + codeISO));
    }

}
