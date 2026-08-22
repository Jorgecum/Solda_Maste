package com.soldaMaster.solda.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetalleVentaResponse {
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuentoProducto;
    private BigDecimal subTotal;
    private LoteResponse idLote;
    private ProductoResponse idProducto;
    private VentaResponse idVenta;

}
