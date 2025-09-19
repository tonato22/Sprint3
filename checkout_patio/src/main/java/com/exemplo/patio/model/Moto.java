package com.exemplo.patio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moto {
    @Id
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}", message = "Placa inválida")
    @Column(unique = true, nullable = false)
    private String placa;

    @NotBlank
    private String modelo;
}