package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class DetalleOrdenRequest {
    private Integer cantidadPedida;
    private BigDecimal precioUnitarioPactado;
    private Integer idOrden;
    private Integer idProducto;
}
