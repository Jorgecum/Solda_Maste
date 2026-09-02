package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class DetalleCompraRequest {
    private Integer cantidad;
    private BigDecimal precioCostoUnitario;
    private Integer idCompra;
    private Integer idLote;
    private String numeroLote;
    private Integer idProducto;
}
