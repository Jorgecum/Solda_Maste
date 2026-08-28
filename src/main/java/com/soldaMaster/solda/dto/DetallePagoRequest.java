package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetallePagoRequest {
    private BigDecimal monto;
    private Integer idCuota;
    private Integer idPago;

}
