package com.soldaMaster.solda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Integer idUsuario;
    private EntidadResponse entidad;
    private String nombreRol;
}
