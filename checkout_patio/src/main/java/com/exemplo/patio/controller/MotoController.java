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

    private final MotoService motoService;

    public MotoController(MotoService motoService) { ;
        this.motoService = motoService;
    }

    @GetMapping
    public ResponseEntity <Page<Moto>> listar(Pageable pageable) { return ResponseEntity.ok( motoService.listar(pageable)); }

    @PostMapping
    public ResponseEntity <Moto> cadastrar(@RequestBody @Valid MotoDTO dto) {
        Moto moto = motoService.cadastrar(dto);
        return ResponseEntity.status(201).body(moto); }

    @GetMapping("/search")
    public ResponseEntity < Page<Moto>> buscarPorPlaca(@RequestParam String placa, Pageable pageable) {
        return ResponseEntity.ok(motoService.buscarPorPlaca(placa, pageable));
    }
    @PutMapping("/{placa}")
    public ResponseEntity<Moto> atualizar(@PathVariable String placa, @RequestBody @Valid MotoDTO dto) {
        Moto motoAtualizada = motoService.atualizar(placa, dto);
        return ResponseEntity.ok(motoAtualizada);
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> deletar(@PathVariable String placa) {
        motoService.deletar(placa);
        return ResponseEntity.noContent().build();
    }



}