package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class DetalleOrdenResponse {
    private Integer idDetalleOrden;
    private Integer cantidadPedida;
    private BigDecimal precioUnitarioPactado;
    private OrdenCompraResponse idOrden;
    private ProductoResponse idProducto;

}
