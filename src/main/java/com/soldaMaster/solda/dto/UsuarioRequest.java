package com.soldaMaster.solda.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequest {
    private String usuario;
    private String password;
    private Integer idEntidad;
    private Integer idEstado;
    private Integer idRol; 
}
