package com.exemplo.patio.service;

import com.exemplo.patio.dto.MotoDTO;
import com.exemplo.patio.model.Moto;
import com.exemplo.patio.repository.MotoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;


@Service
public class MotoService {
    private final MotoRepository repo;
    private final MotoRepository motoRepository;

    public MotoService(MotoRepository repo, MotoRepository motoRepository) {
        this.repo = repo;
        this.motoRepository = motoRepository;
    }

    @Cacheable("motos")
    public Page<Moto> listar(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Moto cadastrar(MotoDTO dto) {
        Moto moto = new Moto(null, dto.getPlaca(), dto.getModelo());
        return repo.save(moto);
    }
    public Page<Moto> buscarPorPlaca(String placa, Pageable pageable) {
        return motoRepository.findByPlacaContainingIgnoreCase(placa, pageable);
    }
    public List<Moto> listarMotos() {
        return repo.findAll();
    }
    public Moto salvar(Moto moto) {
        return repo.save(moto);
    }

}
