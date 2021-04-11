package ru.sberbank.javascool.atm.devices.gateway;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GatewayHttp implements Gateway {

    @Override
    public boolean testLink() {
        return true;
    }

    @Override
    public BigDecimal getBalance(String serialNumber) {
        BigDecimal bigDecimal = new BigDecimal("500000");
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_EVEN);
        return bigDecimal;
    }

}
