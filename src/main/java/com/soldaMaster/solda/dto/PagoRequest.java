package com.soldaMaster.solda.dto;

import java.math.BigDecimal;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagoRequest {
    private BigDecimal montoTotal;
    private String referenciaPago;
    private Integer idMetodoPago;
    private Integer idVenta;
}
