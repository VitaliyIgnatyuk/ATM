package ru.sberbank.javascool.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public abstract class Balance {

    private final Currency currency;

    @Setter
    private BigDecimal summa;

}
