package ru.atmsoft.common.service;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import ru.atmsoft.common.dto.LoginDTO;
import ru.atmsoft.common.dto.SessionDTO;
import ru.atmsoft.common.entity.Card;
import ru.atmsoft.common.exception.CardIsNotReadableException;
import ru.atmsoft.common.exception.LockedCardException;
import ru.atmsoft.common.exception.NonValidSessionException;
import ru.atmsoft.common.exception.NotValidCardException;
import ru.atmsoft.common.repository.CardRepository;
import ru.atmsoft.common.sessions.Session;
import ru.atmsoft.common.sessions.SessionATM;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
@Service
public class SessionsATM implements Sessions {

    private final CardRepository cardRepository;
    /**
     * Связь устройства и сессии
     */
    private Map<String, String> deviceSession = new HashMap<>();
    /**
     * Активные на данный момент сессии
     */
    private Map<String, Session> sessions = new HashMap<>();

    @Override
    public SessionDTO createSession(LoginDTO loginDTO, UnaryOperator<String> sessionIdToUrl) {
        Card card = findActiveCard(loginDTO.getCardNum(), loginDTO.getPin());
        Boolean connected = card.getAttemptsCount().equals(0);
        String Message = connected ? "Авторизация пройдена" : String.format("Осталось %d попыток ввода пин-кода", 3 - card.getAttemptsCount());
        String url = "";
        if (connected) {
            Session session = createSession(loginDTO.getDeviceId(), card.getId());
            url = sessionIdToUrl.apply(session.getSessionId());
        }
        return new SessionDTO(connected, url, Message);
    }

    @Override
    @Synchronized()
    public void closeSession(String sessionId) {
        Optional.ofNullable(sessions.remove(sessionId)).ifPresent(s -> deviceSession.remove(s.getDeviceId()));
    }

    @Override
    @Synchronized()
    public Session getSession(String sessionId) {
        Optional<Session> session = Optional.ofNullable(sessions.computeIfPresent(sessionId, (id, s) -> s.updateSession()));
        if (session.isPresent() && !session.get().getActive()) {
            sessions.remove(sessionId);
            session = Optional.empty();
        }

        return session.orElseThrow(NonValidSessionException::new);
    }

    @Synchronized()
    private Session createSession(String deviceId, UUID cardId) {
        Optional.ofNullable(deviceSession.remove(deviceId)).ifPresent(sId -> sessions.remove(sId));
        Session session = new SessionATM(deviceId, cardId);
        deviceSession.put(deviceId, session.getSessionId());
        sessions.put(session.getSessionId(), session);
        return session;
    }

    private Card findActiveCard(String cardNum, String pin) {
        Card card = cardRepository.findByCardNum(cardNum).orElseThrow(CardIsNotReadableException::new);
        checkValidityCard(card);
        checkCardPin(card, pin);
        return card;
    }

    private void checkCardPin(Card card, String pin) {
        checkLockedCard(card);
        int attemptsCount = card.getAttemptsCount();
        attemptsCount = pin.equals(card.getPin()) ? 0 : ++attemptsCount;
        card.setAttemptsCount(attemptsCount);
        cardRepository.save(card);
        checkLockedCard(card);
    }

    /**
     * Проверка карты на срок действия
     */
    private void checkValidityCard(Card card) {
        if (card.getValidity().isBefore(LocalDate.now()))
            throw new NotValidCardException();
    }

    /**
     * Проверка карты на блокировку
     */
    private void checkLockedCard(Card card) {
        if (card.getAttemptsCount() >= 3)
            throw new LockedCardException();
    }

}
