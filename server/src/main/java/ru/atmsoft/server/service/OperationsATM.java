package ru.atmsoft.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.atmsoft.common.dto.BalanceDTO;
import ru.atmsoft.server.entity.Account;
import ru.atmsoft.server.entity.Card;
import ru.atmsoft.server.exception.NotFoundException;
import ru.atmsoft.server.repository.CardRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationsATM implements Operations {

    private final CardRepository cardRepository;
    private final Sessions sessions;

    @Override
    public BalanceDTO getBalance(String sessionId) {
        UUID cardId = sessions.getSession(sessionId).getCardId();
        Card card = cardRepository.findById(cardId).orElseThrow(NotFoundException::new);
        Account account = card.getAccounts().stream().filter(Account::getMain).findFirst().orElseThrow(NotFoundException::new);
        return new BalanceDTO(account.getCurrency(), account.getAmount());
    }

}
