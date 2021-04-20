package ru.sberbank.javascool.server;

import ru.sberbank.javascool.account.Account;
import ru.sberbank.javascool.card.Card;
import ru.sberbank.javascool.client.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    public void addClient(Client client) {
        clients.add(client);
    }

    private void checkValidSerialNumber(String serialNumber) throws ServerException {
        if (!Pattern.matches("\\d{16}", serialNumber))
            throw new ServerException("невалидный номер карты");
    }

    private void checkValidPin(String pin) throws ServerException {
        if (!Pattern.matches("\\d{3}", pin))
            throw new ServerException("невалидный пин-код");
    }

    private Client findClient(String cardSerialNumber) throws ServerException {
        return clients.stream().filter(c -> c.findAccount(cardSerialNumber).isPresent()).findFirst().
                orElseThrow(() -> new ServerException("отсутсвуют данные о карте"));
    }

    private Account<?> findAccount(Client client, String cardSerialNumber) throws ServerException {
        return client.findAccount(cardSerialNumber).orElseThrow(() -> new ServerException("не найден счёт для переданного номера карты"));
    }

    private Card findCard(Account<?> account, String cardSerialNumber) throws ServerException {
        return account.findCard(cardSerialNumber).orElseThrow(() -> new ServerException("не найдена информация о карте"));
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
