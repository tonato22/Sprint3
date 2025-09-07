package com.exemplo.patio.controller;

import com.exemplo.patio.dto.RegistroDTO;
import com.exemplo.patio.model.Registro;
import com.exemplo.patio.service.RegistroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    private final RegistroService service;

    public RegistroController(RegistroService service) {
        this.service = service;
    }

    // POST /registros  -> check-in
    @PostMapping
    public ResponseEntity<Registro> checkIn(@RequestBody @Valid RegistroDTO dto) {
        Registro registro = service.checkIn(dto);
        return ResponseEntity.status(201).body(registro);
    }

    // PUT /registros/{id} -> check-out
    @PutMapping("/{id}")
    public ResponseEntity<Registro> checkOut(@PathVariable Long id,
                                             @RequestBody @Valid RegistroDTO dto) {
        Registro registro = service.checkOut(id, dto);
        return ResponseEntity.ok(registro);
    }

    // GET /registros?placa=XXX
    @GetMapping
    public ResponseEntity<Page<Registro>> listar(@RequestParam(required = false) String placa,
                                                 Pageable pageable) {
        return ResponseEntity.ok(service.listar(placa, pageable));
    }

    // DELETE /registros/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
