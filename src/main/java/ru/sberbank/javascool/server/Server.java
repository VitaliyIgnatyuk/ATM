package ru.sberbank.javascool.server;

import lombok.Getter;
import ru.sberbank.javascool.account.Account;
import ru.sberbank.javascool.account.Balance;
import ru.sberbank.javascool.card.Card;
import ru.sberbank.javascool.client.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Getter
public class Server {

    private List<Client> clients = new ArrayList<>();

    // сумма, валюта и номер счета
    public AccBalance getBalance(String serialNumber, String pinCode) throws ServerException {
        checkValidSerialNumber(serialNumber);
        Client client = findClient(serialNumber);
        checkValidPin(pinCode);
        Account<?> account = findAccount(client, serialNumber);
        Card card = findCard(account, serialNumber);
        checkExpiredDate(card.getExpiredDate());
        checkPinCode(card.getPinCode(), pinCode);
        return new AccBalance(account.getAccount(), account.getBalance());
    }

    private void checkValidSerialNumber(String serialNumber) throws ServerException {
        if (!Pattern.matches("\\d{16}", serialNumber))
            throw new ServerException("невалидный номер карты");
    }

    private void checkValidPin(String pin) throws ServerException {
        if (!Pattern.matches("\\d{3}", pin))
            throw new ServerException("невалидный пин-код");
    }

    private Client findClient(String serialNumber) throws ServerException {
        Predicate<Client> clientPredicate = c -> c.getAccounts().stream().anyMatch(a -> a.findCard(serialNumber).isPresent());
        return clients.stream().filter(clientPredicate).findFirst().orElseThrow(() -> new ServerException("отсутсвуют данные о карте"));
    }

    private Account<?> findAccount(Client client, String serialNumber) throws ServerException {
        Predicate<Account<?>> accountPredicate = a -> a.findCard(serialNumber).isPresent();
        return client.getAccounts().stream().filter(accountPredicate).findFirst().orElseThrow(() -> new ServerException("не найден счёт для переданного номера карты"));
    }

    private Card findCard(Account<?> account, String serialNumber) throws ServerException {
        Optional<Card> cardOptional = account.findCard(serialNumber);  // по какой то непонятной для меня причине в одну строчку IDEA показывает ошибку на данный код
        return cardOptional.orElseThrow(() -> new ServerException("не найдена информация о карте"));
    }

    private void checkExpiredDate(LocalDate expiredDate) throws ServerException {
        if (expiredDate.compareTo(LocalDate.now()) < 0)
            throw new ServerException("срок действия карты истёк");
    }

    private void checkPinCode(String cardPinCode, String pinCode) throws ServerException {
        if (!cardPinCode.equals(pinCode))
            throw new ServerException("введён не верный пин-код");
    }

}
