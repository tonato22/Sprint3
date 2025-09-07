package com.exemplo.patio.repository;


import com.exemplo.patio.model.Registro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
    Page<Registro> findByMotoPlaca(String placa, Pageable pageable);
    Optional<Registro> findByMotoPlacaAndCheckOutIsNull(String placa);
}