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

public class VentaRequest {

    private Integer idVentaOrigen;
    private String serieCorrelativa;
    private String tipoComprobante;
    private LocalDateTime fechaEmision;
    private BigDecimal total;
    private BigDecimal subtotal;
    private BigDecimal descuentoGlobal;
    private Integer idCliente;
    private Integer idEstadoVenta;
    private Integer idUsuario;
    private List<DetalleVentaRequest>detalleVentasList;
    private List<PagoRequest> pagosList;
    private List<CuotaRequest>cuotasList;
}
