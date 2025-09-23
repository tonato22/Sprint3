package com.exemplo.patio.service;

import com.exemplo.patio.dto.MotoDTO;
import com.exemplo.patio.model.Moto;
import com.exemplo.patio.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;


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
        Moto moto = new Moto(dto.getPlaca(), dto.getModelo());
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
    public Optional<Moto> buscarPorPlacaUnica(String placa) {
        return motoRepository.findByPlaca(placa);
    }
    public void excluir(String placa){
        if (!motoRepository.existsById(placa)) {
            throw new IllegalArgumentException("Moto não encontrada " + placa);
        }
        motoRepository.deleteById(placa);
    }
    public Moto atualizar(String placa, MotoDTO dto) {
        Moto motoExistente = repo.findById(placa)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        motoExistente.setModelo(dto.getModelo());
        return repo.save(motoExistente);
    }
    public void deletar(String placa) {
        Moto moto = repo.findById(placa)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        repo.delete(moto);
    }


}
