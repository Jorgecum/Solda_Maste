package com.soldaMaster.solda.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CompraResponse {
    private Integer idCompra;
    private String tipoComprobante;
    private String serieCorrelativa;
    private LocalDateTime fechaCompra;
    private BigDecimal montoTotal;
    private OrdenCompraResponse idOrden;    
    private EntidadResponse idProveedor;
    private UsuarioResponse idUsuario;
    private List<DetalleCompraResponse> listaDetalleCompra;

}
