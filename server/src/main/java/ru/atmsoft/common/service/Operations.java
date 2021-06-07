package ru.atmsoft.common.service;

import ru.atmsoft.common.dto.BalanceDTO;

public interface Operations {

    BalanceDTO getBalance(String sessionId);

}
