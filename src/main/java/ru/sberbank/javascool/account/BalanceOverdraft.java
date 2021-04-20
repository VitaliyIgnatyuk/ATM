package ru.sberbank.javascool.account;

import java.math.BigDecimal;

public class BalanceOverdraft extends Balance {

    private BigDecimal limit;

    public BalanceOverdraft(Currency currency, BigDecimal summa, BigDecimal limit) {
        super(currency, summa);
        this.limit = limit;
    }

    @Override
    protected void checkNewAmount(BigDecimal amount) throws AccountException {
        if (amount.compareTo(limit) < 0)
            throw new AccountException("превышение лимита овердрафта");
    }

}
