package ru.atmsoft.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class SessionATM implements Session {

    @Getter
    private final String deviceId;
    @Getter
    private final UUID cardId;
    @Getter
    private final String sessionId = UUID.randomUUID().toString(); // "testSessionId"
    private LocalDateTime updateTime = LocalDateTime.now();

    @Override
    public Session updateSession() {
        if (getActive())
            updateTime = LocalDateTime.now();
        return this;
    }

    @Override
    public boolean getActive() {
        return LocalDateTime.now().isBefore(updateTime.plusMinutes(5));
    }

}
