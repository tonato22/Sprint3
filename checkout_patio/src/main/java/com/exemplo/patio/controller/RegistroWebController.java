package com.exemplo.patio.controller;

import com.exemplo.patio.dto.RegistroDTO;
import com.exemplo.patio.model.Moto;
import com.exemplo.patio.model.Registro;
import com.exemplo.patio.repository.MotoRepository;
import com.exemplo.patio.repository.RegistroRepository;
import com.exemplo.patio.service.MotoService;
import com.exemplo.patio.service.RegistroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    // LISTAR REGISTROS
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("registros", registroService.listarTodos());
        return "registros/listar";
    }

    // FORMULÁRIO NOVO REGISTRO
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("registro", new Registro());
        model.addAttribute("motos", motoService.listarMotos()); // se quiser lista de motos
        return "registros/novo";
    }

    // SALVAR CHECK-IN
    @PostMapping
    public String salvar(@ModelAttribute Registro registro, @RequestParam Long motoId) {
        registroService.salvarRegistro(registro, motoId);
        return "redirect:/registros";
    }

    // CHECK-OUT
    @PostMapping("/checkout/{id}")
    public String checkOut(@PathVariable Long id, @RequestParam RegistroDTO registroDTO) {
        registroService.checkOut(id, registroDTO);
        return "redirect:/registros";
    }

    // EDITAR REGISTROS
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Registro registro = registroRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Registro Invalido " + id));
        model.addAttribute("registro", registro);
        return "registros/form";
    }

    // ATUALIZAR OS REGISTROS
    @PostMapping("editar/{id}")
    public String atualizarRegistro(@PathVariable Long id, @ModelAttribute Registro registroAtualizado){
        registroAtualizado.setId(id);
        registroService.salvarRegistro(registroAtualizado,id);
        return "redirect:/registros";
    }

    // FAZER O DELETE



}
