package com.exemplo.patio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(Model model, String error, String logout) {
        if (error != null) model.addAttribute("errorMsg", "Usuário ou senha inválidos.");
        if (logout != null) model.addAttribute("msg", "Logout realizado.");
        return "login/login";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home/home";
    }
}
