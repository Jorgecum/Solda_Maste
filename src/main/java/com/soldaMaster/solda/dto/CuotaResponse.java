package com.soldaMaster.solda.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CuotaResponse {
    private Integer idCuota;
    private Integer numeroCuota;
    private LocalDate fechaVencimiento;
    private BigDecimal monto;
    private EstadoResponse idEstadoCuota;
    private VentaResponse idVenta;

}
