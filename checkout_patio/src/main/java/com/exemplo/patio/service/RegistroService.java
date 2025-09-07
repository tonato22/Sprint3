package com.exemplo.patio.service;

import ch.qos.logback.core.model.Model;
import com.exemplo.patio.dto.RegistroDTO;
import com.exemplo.patio.model.Moto;
import com.exemplo.patio.model.Registro;
import com.exemplo.patio.repository.MotoRepository;
import com.exemplo.patio.repository.RegistroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroService {
    private final RegistroRepository registroRepo;
    private final MotoRepository motoRepo;

    public RegistroService(RegistroRepository registroRepo, MotoRepository motoRepo) {
        this.registroRepo = registroRepo;
        this.motoRepo = motoRepo;
    }

    public Registro checkIn(RegistroDTO dto) {
        Moto moto = motoRepo.findByPlaca(dto.getPlaca()).orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        registroRepo.findByMotoPlacaAndCheckOutIsNull(dto.getPlaca()).ifPresent(r -> { throw new IllegalStateException("Moto já no pátio"); });
        return registroRepo.save(new Registro(null, moto, LocalDateTime.now(), null));
    }

    @Transactional
    public Registro checkOut(Long id ,RegistroDTO dto) {
        Registro reg = registroRepo.findByMotoPlacaAndCheckOutIsNull(dto.getPlaca()).orElseThrow(() -> new EntityNotFoundException("Registro de entrada não encontrado"));
        reg.setCheckOut(LocalDateTime.now());
        return reg;
    }
    public void delete(Long id) {
        if (!registroRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Registro não encontrado");
        }
        registroRepo.deleteById(id);
    }

    public Page<Registro> listar(String placa, Pageable pageable) {
        return registroRepo.findByMotoPlaca(placa, pageable);
    }

    public List<Registro> listarTodos() {
        return registroRepo.findAll();
    }

    public String salvarRegistro(Registro registro, Long motoId) {
        Moto moto = motoRepo.findById(motoId)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));

        registro.setMoto(moto);
        registro.setCheckIn(LocalDateTime.now()); // marca check-in
        registroRepo.save(registro);

        return "redirect:/registros";
    }


}