package ru.atmsoft.client.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.atmsoft.client.form.Operation;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class OperationsATM implements Operations {

    private List<Operation> operations = new ArrayList<>();

    @PostConstruct
    private void postConstruct() {
        operations.add(new Operation("Запрос баланса", "operations/balance"));
    }

}
