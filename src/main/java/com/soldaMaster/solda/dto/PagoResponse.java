package com.soldaMaster.solda.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagoResponse {
    private Integer idPago;
    private BigDecimal montoTotal;
    private LocalDateTime fecha;
    private String referenciaPago;
    private MetodoPagoResponse idMetodoPago;
    private VentaResponse idVenta;
}
