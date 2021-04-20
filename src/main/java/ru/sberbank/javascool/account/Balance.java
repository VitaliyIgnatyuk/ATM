package ru.sberbank.javascool.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public abstract class Balance {

    private final Currency currency;

    private BigDecimal amount;

    public void setAmount(BigDecimal amount) throws AccountException {
        checkNewAmount(amount);
        this.amount = amount;
    }

    protected void checkNewAmount(BigDecimal amount) throws AccountException {}

}
