package com.exemplo.patio.controller;

import com.exemplo.patio.model.Moto;
import com.exemplo.patio.service.MotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MotoViewController {

    private final MotoService service;

    public MotoViewController(MotoService service) {
        this.service = service;
    }

    @GetMapping("/motos")
    public String listar(Model model) {
        model.addAttribute("motos", service.listarMotos());
        return "motos/listar"; // carrega templates/motos/listar.html
    }

    @GetMapping("/motos/novo")
    public String novo(Model model) {
        model.addAttribute("moto", new Moto());
        return "motos/novo";
    }

    @PostMapping
    public String salvar(@ModelAttribute Moto moto){
        service.salvar(moto);
        return "redirect:/motos";
    }
}
