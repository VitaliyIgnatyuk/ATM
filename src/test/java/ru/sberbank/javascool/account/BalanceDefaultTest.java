package ru.sberbank.javascool.account;

import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class BalanceDefaultTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Getter
    @Setter
    private Balance balance = new BalanceDefault(Currency.RUB, new BigDecimal("12.34"));

    @Test
    public void getCurrency() {
        Assert.assertEquals(balance.getCurrency(), Currency.RUB);
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
        thrown.expect(AccountException.class);
        thrown.expectMessage("баланс не может быть меньше 0");
        balance.setSumma(new BigDecimal("-1.01"));
        thrown = ExpectedException.none();
    }
}