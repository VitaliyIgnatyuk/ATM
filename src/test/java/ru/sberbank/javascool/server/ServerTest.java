package ru.sberbank.javascool.server;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import ru.sberbank.javascool.account.Account;
import ru.sberbank.javascool.account.Balance;
import ru.sberbank.javascool.account.Currency;
import ru.sberbank.javascool.card.BankCard;
import ru.sberbank.javascool.card.Card;
import ru.sberbank.javascool.client.Client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final String account = "40817810099910004312";
    private final String validSerialNumber = "5555555555554444";
    private final String expiredSerialNumber = "3333333333334444";
    private final String validPinCode = "000";
    private final BigDecimal summa = new BigDecimal("12345678.01");
    private final Currency currency = Currency.RUB;

    private Server server;

    @Before
    public void setUp() {
        server = new Server();
        server.addClient(createClientMock());
    }

    @SneakyThrows(ServerException.class)
    @Test
    public void getBalance() {
        AccBalance accBalance = server.getBalance(validSerialNumber, validPinCode);
        Assert.assertEquals(accBalance.getAccount(), account);
        Assert.assertEquals(accBalance.getBalance().getCurrency(), currency);
        Assert.assertSame(accBalance.getBalance().getAmount().compareTo(summa),0);
    }

    @SneakyThrows(ServerException.class)
    @Test
    public void checkValidSerialNumber() {
        thrown.expect(ServerException.class);
        thrown.expectMessage("невалидный номер карты");
        server.getBalance("1234567890", validPinCode);
        thrown = ExpectedException.none();
    }

    @SneakyThrows(ServerException.class)
    @Test
    public void findClient() {
        thrown.expect(ServerException.class);
        thrown.expectMessage("отсутсвуют данные о карте");
        server.getBalance("1111111111112222", validPinCode);
        thrown = ExpectedException.none();
    }

    @SneakyThrows(ServerException.class)
    @Test
    public void checkValidPin() {
        thrown.expect(ServerException.class);
        thrown.expectMessage("невалидный пин-код");
        server.getBalance(validSerialNumber, "0000");
        thrown = ExpectedException.none();
    }

    @SneakyThrows(ServerException.class)
    @Test
    public void checkExpiredDate() {
        thrown.expect(ServerException.class);
        thrown.expectMessage("срок действия карты истёк");
        server.getBalance(expiredSerialNumber, validPinCode);
        thrown = ExpectedException.none();
    }

    @SneakyThrows(ServerException.class)
    @Test
    public void checkPinCode() {
        thrown.expect(ServerException.class);
        thrown.expectMessage("введён не верный пин-код");
        server.getBalance(validSerialNumber, "111");
        thrown = ExpectedException.none();
    }

    private Client createClientMock() {
        Client client = mock(Client.class);
        Optional<Account<?>> account = createAccountMock();
        when(client.findAccount(Matchers.any())).thenReturn(Optional.empty());
        when(client.findAccount(Matchers.eq(validSerialNumber))).thenReturn(account);
        when(client.findAccount(Matchers.eq(expiredSerialNumber))).thenReturn(account);
        return client;
    }

    private Optional<Card> createValidCardMock() {
        Card card = mock(BankCard.class);
        when(card.getExpiredDate()).thenReturn(LocalDate.now().plusDays(10));
        when(card.getPinCode()).thenReturn(validPinCode);
        return Optional.of(card);
    }

    private Optional<Card> createExpiredCardMock() {
        Card card = mock(BankCard.class);
        when(card.getExpiredDate()).thenReturn(LocalDate.now().minusDays(10));
        return Optional.of(card);
    }

    private Optional<Account<?>> createAccountMock() {
        @SuppressWarnings("unchecked")
        Account<Balance> account = mock(Account.class);
        when(account.getAccount()).thenReturn(this.account);
        Balance balance = createBalanceMock();
        when(account.getBalance()).thenReturn(balance);

        Optional<Card> card = createValidCardMock();
        when(account.findCard(validSerialNumber)).thenReturn(card);
        card = createExpiredCardMock();
        when(account.findCard(expiredSerialNumber)).thenReturn(card);
        return Optional.of(account);
    }

    private Balance createBalanceMock() {
        Balance balance = mock(Balance.class);
        when(balance.getCurrency()).thenReturn(currency);
        when(balance.getAmount()).thenReturn(summa);
        return balance;
    }

}