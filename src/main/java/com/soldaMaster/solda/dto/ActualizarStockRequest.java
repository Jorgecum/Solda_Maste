package com.soldaMaster.solda.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActualizarStockRequest {
    private Integer idProducto;
    private Integer idTipoMovimiento;
    private Integer cantidad;
    private Integer idLote;
    private String referencia;
}
