package ru.sberbank.javascool.account;

import java.math.BigDecimal;

public class BalanceDefault extends Balance {

    public BalanceDefault(Currency currency, BigDecimal summa) {
        super(currency, summa);
    }

    @Override
    protected void checkNewAmount(BigDecimal amount) throws AccountException {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new AccountException("баланс не может быть меньше 0");
    }

}
