package ru.sberbank.javascool.account;

import org.junit.Assert;
import org.junit.Test;
import ru.sberbank.javascool.card.BankCard;
import ru.sberbank.javascool.card.Card;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountTest {

    private final String accNum = "40817810099910004312";

    private Balance balance = mock(BalanceDefault.class);

    private Account<?> account = new Account<>(accNum, balance);

    @Test
    public void findCard() {
        final String testSerialNumber = "5555555555554444";
        Card card = mock(BankCard.class);
        when(card.getSerialNumber()).thenReturn("4242424242424242");
        account.addCard(card);
        Card testCard = mock(BankCard.class);
        when(testCard.getSerialNumber()).thenReturn(testSerialNumber);
        account.addCard(testCard);
        card = mock(BankCard.class);
        when(card.getSerialNumber()).thenReturn("2201382000000013");
        account.addCard(card);
        Assert.assertTrue(account.findCard(testCard.getSerialNumber()).isPresent());
        Assert.assertSame(account.findCard(testSerialNumber).orElse(null), testCard);
        Assert.assertFalse(account.findCard("4012888888881881").isPresent());
    }

    @Test
    public void getAccount() {
        Assert.assertSame(account.getAccount(), accNum);
    }

    @Test
    public void getBalance() {
        Assert.assertSame(account.getBalance(), balance);
    }
}