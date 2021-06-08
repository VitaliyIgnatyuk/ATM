package ru.atmsoft.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.atmsoft.client.service.ClientService;
import ru.atmsoft.client.service.Operations;

@AllArgsConstructor
@Controller
@RequestMapping("operations")
public class OperationController {

    private final ClientService clientService;
    private final Operations operations;

    @GetMapping
    public String operations(Model model) {
        model.addAttribute("ClientName", clientService.ClientName());
        model.addAttribute("operations", operations.getOperations());
        return "operations";
    }

    @PostMapping("/balance")
    public String getBalance(Model model) {
        model.addAttribute("balanceDTO", clientService.balance());
        return "balance";
    }

}
