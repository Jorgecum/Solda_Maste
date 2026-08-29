package com.soldaMaster.solda.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class OrdenCompraRequest {
    private LocalDateTime fechaPedido;
    private LocalDate fechaEntrega;
    private BigDecimal totalEstimado;
    private Integer idProveedor;
    private Integer idEstadoOrden;
    private Integer idUsuario;
    private List<DetalleOrdenRequest> listaDetalles;
}
