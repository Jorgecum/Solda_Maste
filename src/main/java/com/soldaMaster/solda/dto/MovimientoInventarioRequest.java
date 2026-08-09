package com.soldaMaster.solda.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class MovimientoInventarioRequest {

    private int cantidad;
    private LocalDateTime fecha;
    private String referencia;
    private Integer idLote;
    private Integer idProducto;
    private Integer idTipoMovimiento;

}
