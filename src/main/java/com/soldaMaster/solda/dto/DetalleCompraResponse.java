package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class DetalleCompraResponse {
    private Integer idDetalleCompra;
    private Integer cantidad;
    private BigDecimal precioCostoUnitario;
    private CompraResponse idCompra;
    private LoteResponse idLote;
    private ProductoResponse idProducto;
}
