package com.soldaMaster.solda.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstadoRequest {

    @NotNull
    @Size(min = 1, max = 20)
    private String tipoCodigo;

    @NotNull
    @Size(min = 1, max = 50)
    private String nombre;
}