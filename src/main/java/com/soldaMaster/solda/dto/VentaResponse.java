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
public class VentaResponse {
    private Integer idVenta;
    private Integer idVentaOrigen;
    private String tipoComprobante;
    private LocalDateTime fechaEmision;
    private String serieCorrelativa;
    private BigDecimal total;
    private BigDecimal subtotal;
    private BigDecimal descuentoGlobal;
    private EntidadResponse idCliente;
    private EstadoResponse idEstadoVenta;
    private UsuarioResponse idUsuario;
    private List<DetalleVentaResponse>detallesVentasList;

}
