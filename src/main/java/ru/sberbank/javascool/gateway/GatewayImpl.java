package ru.sberbank.javascool.gateway;

public class GatewayImpl implements Gateway {

    @Override
    public boolean testLink() {
        return true;
    }

    @Override
    public double getBalance(String serialNumber) {
        return 500000d;
    }

}
