package ru.sberbank.javascool.account;

import lombok.Getter;

@Getter
public enum Currency {

    EUR("Euro","978"),
    USD("US Dollar", "840"),
    CNY("Yuan Renminbi", "156"),
    RUB("Russian Ruble", "643"),
    GBP("Pound Sterling",	"826");

    private String name;
    private String codeISO;

    Currency(String name, String codeISO) {
        this.name = name;
        this.codeISO = codeISO;
    }

}
