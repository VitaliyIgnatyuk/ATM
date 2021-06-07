package ru.atmsoft.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.atmsoft.client.exception.ClientResponseErrorHandler;
import ru.atmsoft.client.form.LoginForm;
import ru.atmsoft.common.dto.BalanceDTO;
import ru.atmsoft.common.dto.LoginDTO;
import ru.atmsoft.common.dto.SessionDTO;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceATM implements ClientService {

    @Value("${serverUrl:http://127.0.0.1:8080}")
    private String serverUrl;
    private static final String deviceId = UUID.randomUUID().toString(); // имитация уникального номера оборудования
    private final ClientResponseErrorHandler clientResponseErrorHandler;
    private String url = "";

    @Override
    public SessionDTO login(LoginForm loginForm) {
        LoginDTO loginDTO = new LoginDTO(deviceId, loginForm.getCardNum(), loginForm.getPin());
        SessionDTO sessionDTO = restTemplate().postForObject(serverUrl + "/createSession", loginDTO, SessionDTO.class);
        if (Objects.requireNonNull(sessionDTO).getConnected()) {
            url = sessionDTO.getUrl();
            log.info("Для пользователя с номером карты '{}' получена сессия: {}", loginForm.getCardNum(), url);
        }
        return sessionDTO;
    }

    @Override
    public String ClientName() {
        return restTemplate().getForObject(sessionUrl("/clientName"), String.class);
    }

    @Override
    public BalanceDTO balance() {
        String url =sessionUrl("/balance");
        return restTemplate().getForObject(url, BalanceDTO.class);
    }

    @Override
    public void exit() {
        restTemplate().delete(sessionUrl("/closeSession"));
    }

    private String sessionUrl(String adr) {
        return url + adr;
    }

    private RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(clientResponseErrorHandler);
        return restTemplate;
    }

}
