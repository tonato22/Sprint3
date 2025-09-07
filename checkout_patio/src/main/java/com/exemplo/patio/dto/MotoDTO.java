package com.exemplo.patio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MotoDTO {
    @NotBlank
    private String placa;

    @NotBlank
    private String modelo;


}