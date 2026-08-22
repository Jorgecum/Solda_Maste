package com.soldaMaster.solda.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CuotaRequest {
    private Integer numeroCuota;
    private LocalDate fechaVencimiento;
    private BigDecimal monto;
    private Integer idEstadoCuota;
    private Integer idVenta;
}
