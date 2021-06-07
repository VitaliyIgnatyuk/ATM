package ru.atmsoft.client.service;

import ru.atmsoft.client.form.LoginForm;
import ru.atmsoft.common.dto.BalanceDTO;
import ru.atmsoft.common.dto.SessionDTO;

public interface ClientService {

    SessionDTO login(LoginForm loginForm);

    String ClientName();

    BalanceDTO balance();

    void exit();

}
