package ru.sberbank.javascool.account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BalanceOverdraftTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Balance balance = new BalanceOverdraft(Currency.GBP, new BigDecimal("12.34"), new BigDecimal("-100"));

    @Test
    public void getCurrency() {
        Assert.assertEquals(balance.getCurrency(), Currency.GBP);
        Assert.assertNotEquals(balance.getCurrency(), Currency.EUR);
    }

    @Test
    public void getSumma() {
        Assert.assertEquals(balance.getSumma().compareTo(new BigDecimal("12.34")), 0);
        Assert.assertNotEquals(balance.getSumma().compareTo(new BigDecimal("12.35")), 0);
    }

    @Test
    public void setSumma() {
        balance.setSumma(new BigDecimal("67.89"));
        Assert.assertEquals(balance.getSumma().compareTo(new BigDecimal("67.89")), 0);
        balance.setSumma(new BigDecimal("-1.01"));
        Assert.assertEquals(balance.getSumma().compareTo(new BigDecimal("-1.01")), 0);
        thrown.expect(AccountException.class);
        thrown.expectMessage("превышение лимита овердрафта");
        balance.setSumma(new BigDecimal("-100.01"));
        thrown = ExpectedException.none();
    }
}