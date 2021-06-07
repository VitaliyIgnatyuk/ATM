package ru.atmsoft.common.service;

import ru.atmsoft.common.dto.LoginDTO;
import ru.atmsoft.common.dto.SessionDTO;
import ru.atmsoft.common.Session;

import java.util.function.UnaryOperator;

public interface Sessions {

    SessionDTO createSession(LoginDTO loginDTO, UnaryOperator<String> sessionIdToUrl);

    void closeSession(String sessionId);

    Session getSession(String sessionId);

}
