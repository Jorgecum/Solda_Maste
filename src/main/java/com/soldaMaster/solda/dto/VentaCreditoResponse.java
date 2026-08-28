package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VentaCreditoResponse {
    private Integer idVenta;
    private String serieCorrelativa;
    private BigDecimal total;
    private BigDecimal totalPendiente;
    private String estado; 
    private EntidadResponse cliente;
}
