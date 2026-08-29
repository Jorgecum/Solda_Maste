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

public class OrdenCompraResponse {
    private Integer idOrden;
    private LocalDateTime fechaPedido;
    private LocalDate fechaEntrega;
    private BigDecimal totalEstimado;
    private EntidadResponse idProveedor;
    private EstadoResponse idEstadoOrden;
    private UsuarioResponse idUsuario;
    private List<DetalleOrdenResponse> listaDetalles;

}
