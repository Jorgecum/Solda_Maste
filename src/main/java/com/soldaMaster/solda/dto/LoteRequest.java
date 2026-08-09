package com.soldaMaster.solda.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteRequest {
    private String numeroLote;
    private LocalDateTime fechaEntrada;
    private Integer stockLote;
    private Integer idCertificado;
    private Integer idProducto;

}
