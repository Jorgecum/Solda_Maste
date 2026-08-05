package com.soldaMaster.solda.dto;

import com.soldaMaster.solda.entity.Roles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioResponse {
    private Integer idUsuario;
    private String usuario;
    private String password;
    private EntidadResponse idEntidad;
    private EstadoResponse idEstado;
    private Roles idRol;

}
