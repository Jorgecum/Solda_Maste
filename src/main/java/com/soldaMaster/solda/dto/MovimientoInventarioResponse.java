package com.soldaMaster.solda.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovimientoInventarioResponse {
    private Integer idMovimiento;
    private int cantidad;
    private LocalDateTime fecha;
    private String referencia;
    private LoteResponse idLote;
    private ProductoResponse idProducto;
    private TipoMovimientoResponse idTipoMovimiento;

}
