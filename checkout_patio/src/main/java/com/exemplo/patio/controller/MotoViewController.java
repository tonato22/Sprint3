package com.exemplo.patio.controller;

import com.exemplo.patio.model.Moto;
import com.exemplo.patio.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class MotoViewController {


    private final MotoService motoService;

    public MotoViewController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping("/motos")
    public String listar(Model model) {
        model.addAttribute("motos", motoService.listarMotos());
        return "motos/listar"; // carrega templates/motos/listar.html
    }

    @GetMapping("/motos/novo")
    public String novo(Model model) {
        model.addAttribute("moto", new Moto());
        return "motos/novo";
    }

    @PostMapping("/motos")
    public String salvar(@Valid @ModelAttribute Moto moto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("moto", moto);
            return "motos/novo";
        }
        motoService.salvar(moto);
        return "redirect:/motos";
    }

    @GetMapping("/motos/editar/{placa}")
    public String editar(@PathVariable("placa") String placa, Model model) {
        Moto moto = motoService.buscarPorPlacaUnica(placa)
                .orElseThrow(() -> new IllegalArgumentException("Moto não encontrada: " + placa));
        model.addAttribute("moto", moto);
        return "motos/form";
    }

    @PostMapping("/motos/editar/{placa}")
    public String atualizar(@PathVariable String placa,
                            @Valid @ModelAttribute Moto motoAtualizada,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("moto", motoAtualizada);
            return "motos/form";
        }
        motoAtualizada.setPlaca(placa);
        motoService.salvar(motoAtualizada);
        return "redirect:/motos";
    }



    @PostMapping("/excluir/{placa}")
    public String excluir(@PathVariable String placa) {
        motoService.excluir(placa);
        return "redirect:/motos";
    }


}
