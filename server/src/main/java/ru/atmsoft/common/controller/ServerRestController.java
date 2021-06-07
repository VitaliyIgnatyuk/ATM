package ru.atmsoft.common.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.atmsoft.common.dto.BalanceDTO;
import ru.atmsoft.common.dto.LoginDTO;
import ru.atmsoft.common.dto.SessionDTO;
import ru.atmsoft.common.service.ClientInfo;
import ru.atmsoft.common.service.Operations;
import ru.atmsoft.common.service.Sessions;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.function.UnaryOperator;

@Slf4j
@RestController
@AllArgsConstructor
public class ServerRestController {

    private final Sessions sessions;
    private final ClientInfo clientInfo;
    private final Operations operations;


    @GetMapping("/{sessionId}/clientName")
    public String clientName(@PathVariable @NotEmpty String sessionId) {
        log.info("Запрос имени клиента для сессии: {}", sessionId);
        return clientInfo.FullName(sessionId);
    }

    @GetMapping("/{sessionId}/balance")
    public BalanceDTO getBalance(@PathVariable @NotEmpty String sessionId) {
        log.info("Запрос баланса карты клиента для сессии: {}", sessionId);
        return operations.getBalance(sessionId);
    }

    /** Создание новой сессии для устройства */
    @PostMapping("/createSession")
    public ResponseEntity<Object> createSession(@Valid @RequestBody LoginDTO loginDTO, UriComponentsBuilder builder) {
        log.info("Создание сессии для пользователя с номером карты: {}", loginDTO.getCardNum());
        UnaryOperator<String> sessionIdToLink = session -> builder.path("/{sessionId}/").buildAndExpand(session).toUriString();
        SessionDTO sessionDTO = sessions.createSession(loginDTO, sessionIdToLink);
        if (sessionDTO.getConnected())
            log.info("Создана сессия '{}' для пользователя с номером карты: {}", sessionDTO.getUrl(), loginDTO.getCardNum());
        else
            log.info("При создании сессии для пользователя с номером карты '{}' произошла ошибка: {}", loginDTO.getCardNum(), sessionDTO.getMessage());
        return new ResponseEntity<>(sessionDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{sessionId}/closeSession")
    public void closeSession(@PathVariable @NotEmpty String sessionId) {
        log.info("Закрытие сессии {} ", sessionId);
        sessions.closeSession(sessionId);
    }

}
