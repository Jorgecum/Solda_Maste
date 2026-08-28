package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetallePagoResponse {
    private Integer idDetallePago;
    private BigDecimal monto;
    private CuotaResponse idCuota;
    private PagoResponse idPago;
}
