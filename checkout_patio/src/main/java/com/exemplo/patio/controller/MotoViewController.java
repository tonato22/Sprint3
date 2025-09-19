package com.exemplo.patio.controller;

import com.exemplo.patio.model.Moto;
import com.exemplo.patio.service.MotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping
    public String salvar(@ModelAttribute Moto moto){
        motoService.salvar(moto);
        return "redirect:/motos";
    }
    @GetMapping("/editar/{placa}")
    public String editar(@PathVariable("placa") String placa, Model model) {
        Moto moto = motoService.buscarPorPlacaUnica(placa).orElseThrow(()->new IllegalArgumentException("Moto não encontrada" + placa));
        model.addAttribute("moto", moto);
        return "motos/form";
    }

    @PostMapping("/editar/{placa}")
    public String atualizar(@PathVariable String placa,@ModelAttribute Moto motoAtualizada) {
        motoAtualizada.setPlaca(placa);
        motoService.salvar(motoAtualizada);
        return "redirect:/motos";
    }

    //FAZER O DELETE


}
