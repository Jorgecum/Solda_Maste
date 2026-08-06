package com.soldaMaster.solda.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EntidadRequest {

    @Size(max = 20)
    private String tipoDocumento;

    @Size(max = 20)
    private String numeroDocumento;

    @NotNull
    @Size(min = 1, max = 150)
    private String nombreRazonSocial;

    @Size(max = 250)
    private String direccion;

    @Size(max = 20)
    private String telefono;

    @Size(max = 100)
    private String email;

    @NotNull
    private Integer idEstado;

    private Integer idTipoEntidad;
}