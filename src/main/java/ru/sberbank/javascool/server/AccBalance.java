package ru.sberbank.javascool.server;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.sberbank.javascool.account.Balance;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class AccBalance {

    private String account;

    private Balance balance;

}
