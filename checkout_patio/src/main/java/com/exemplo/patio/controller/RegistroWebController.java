package com.exemplo.patio.controller;

import com.exemplo.patio.dto.RegistroDTO;
import com.exemplo.patio.model.Moto;
import com.exemplo.patio.model.Registro;
import com.exemplo.patio.repository.MotoRepository;
import com.exemplo.patio.repository.RegistroRepository;
import com.exemplo.patio.service.MotoService;
import com.exemplo.patio.service.RegistroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Controller
@RequestMapping("/registros")
public class RegistroWebController {

    private final RegistroRepository registroRepo;
    private final MotoRepository motoRepo;
    private final RegistroService registroService;
    private final MotoService motoService;


    public RegistroWebController(RegistroRepository registroRepo, MotoRepository motoRepo, RegistroService registroService, MotoService motoService) {
        this.registroRepo = registroRepo;
        this.motoRepo = motoRepo;
        this.registroService = registroService;
        this.motoService = motoService;
    }


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("registros", registroService.listarTodos());
        return "registros/listar";
    }


    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("registro", new Registro());
        model.addAttribute("motos", motoService.listarMotos()); // se quiser lista de motos
        return "registros/novo";
    }


    @PostMapping
    public String salvar(@Valid  @ModelAttribute Registro registro,BindingResult result, @RequestParam String placa,Model model ) {
        if (result.hasErrors()) {
            model.addAttribute("motos", motoService.listarMotos());
            return "registros/novo";
        }
        registroService.salvarRegistro(registro, placa);
        return "redirect:/registros";
    }


    @PostMapping("/checkout/{id}")
    public String checkOut(@PathVariable Long id, @RequestParam RegistroDTO registroDTO) {
        registroService.checkOut(id, registroDTO);
        return "redirect:/registros";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Registro registro = registroRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Registro Invalido " + id));
        model.addAttribute("registro", registro);
        return "registros/form";
    }


    @PostMapping("editar/{id}")
    public String atualizarRegistro(@PathVariable Long id,
                                    @RequestParam String placa,
                                    @Valid @ModelAttribute Registro registroAtualizado,BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("motos", motoService.listarMotos());
            return "registros/form";
        }


      Moto moto = motoRepo.findById(placa)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));

        Registro registroExistente = registroRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro não encontrado"));

        registroExistente.setMoto(moto);
        registroExistente.setCheckIn(LocalDateTime.now());



        registroRepo.save(registroExistente);

        return "redirect:/registros";
    }


    @PostMapping("/delete/{id}")
    public String deletarRegistro(@PathVariable Long id) {
        Registro registro = registroRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro não encontrado"));

        registroRepo.delete(registro);

        return "redirect:/registros";
    }


}
