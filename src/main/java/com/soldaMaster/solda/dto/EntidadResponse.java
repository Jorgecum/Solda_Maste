package com.soldaMaster.solda.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EntidadResponse {
    private Integer idEntidad;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombreRazonSocial;
    private String direccion;
    private String telefono;
    private String email;
    private EstadoResponse idEstado;
    private TipoEntidadResponse idTipoEntidad;
}