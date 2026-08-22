package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetalleVentaRequest {
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuentoProducto;
    private BigDecimal subTotal;
    private Integer idLote;
    private Integer idProducto;
    private Integer idVenta;

}
