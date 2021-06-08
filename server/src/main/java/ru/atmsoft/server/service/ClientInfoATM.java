package ru.atmsoft.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.atmsoft.server.exception.NotFoundException;
import ru.atmsoft.server.repository.CardRepository;

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

}
