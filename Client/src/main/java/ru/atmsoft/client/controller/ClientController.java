package ru.atmsoft.client.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.atmsoft.client.exception.ErrorInfo;
import ru.atmsoft.client.form.LoginForm;
import ru.atmsoft.client.service.ClientService;
import ru.atmsoft.common.dto.SessionDTO;

@Slf4j
@AllArgsConstructor
@Controller
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String index(Model model) {
        return "redirect:index";
    }

    @GetMapping("/index")
    public String start(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginPOST(Model model, @ModelAttribute("loginForm") LoginForm loginForm) {
        log.info("Логин на сервер пользователя с номером карты: {}", loginForm.getCardNum());
        SessionDTO sessionDTO = clientService.login(loginForm);
        if (sessionDTO.getConnected()) {
            log.info("Логин выполнен");
            return "redirect:/operations";
        }
        log.info("Логин не выполнен из-за ошибки: {}", sessionDTO.getMessage());
        model.addAttribute("errorMessage", sessionDTO.getMessage());
        return "login";
    }

    /* Существует ли возможность при редиректе передать в теле сообщения объект???*/
    @GetMapping("/errorInfo/{errorMessage}")
    public String errorInfo(Model model, @PathVariable String errorMessage) {
        model.addAttribute("ErrorInfo", new ErrorInfo(errorMessage));
        return "errorInfo";
    }

    @PostMapping("/exit")
    public String exit(Model model) {
        log.info("Выход текущего пользователя");
        clientService.exit();
        return "redirect:/index";
    }

}
