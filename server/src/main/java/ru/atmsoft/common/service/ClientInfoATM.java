package ru.atmsoft.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.atmsoft.common.exception.NotFoundException;
import ru.atmsoft.common.repository.CardRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientInfoATM implements ClientInfo {

    private final Sessions sessions;
    private final CardRepository cardRepository;

    @Transactional
    @Override
    public String FullName(String sessionId) {
        UUID cardId = sessions.getSession(sessionId).getCardId();
        return cardRepository.findById(cardId).orElseThrow(NotFoundException::new).getClient().getFullName();
    }

//    private void test() {
//        Client client = new Client();
//        client.setNumber(55l);
//        client.setFullName("Тест");
//        client.setCards(new HashSet<Card>());
//
//        Card card = new Card();
//        card.setCardNum("4111111111111119");
//        card.setPin("0000");
//        card.setAttemptsCount(0);
//        card.setValidity(LocalDate.now().plusYears(3));
//        card.setClient(client);
//        card.setAccounts(new HashSet<Account>());
//        client.getCards().add(card);
//
//        Account account = new Account();
//        account.setAccountNum("40817156099910004555");
//        account.setCurrency(Currency.EUR);
//        account.setAmount(BigDecimal.TEN);
//        account.setMain(Boolean.TRUE);
//        account.setCard(card);
//        card.getAccounts().add(account);
//
//        clientRepository.save(client);
//    }
/*
SELECT * FROM CLIENT AS CL
LEFT OUTER JOIN CARD AS CA
ON CL.id = CA.client_id
LEFT OUTER JOIN ACCOUNT AS A
ON CA.id = A.card_id
WHERE CL.number = 55
 */
}
