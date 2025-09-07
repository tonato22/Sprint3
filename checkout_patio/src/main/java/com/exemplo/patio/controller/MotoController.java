package com.exemplo.patio.controller;

import com.exemplo.patio.dto.MotoDTO;
import com.exemplo.patio.model.Moto;
import com.exemplo.patio.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/motos")
public class MotoController {
    private final MotoService service;

    public MotoController(MotoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity <Page<Moto>> listar(Pageable pageable) { return ResponseEntity.ok( service.listar(pageable)); }

    @PostMapping
    public ResponseEntity <Moto> cadastrar(@RequestBody @Valid MotoDTO dto) {
        Moto moto = service.cadastrar(dto);
        return ResponseEntity.status(201).body(moto); }

    @GetMapping("/search")
    public ResponseEntity < Page<Moto>> buscarPorPlaca(@RequestParam String placa, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorPlaca(placa, pageable));
    }

}