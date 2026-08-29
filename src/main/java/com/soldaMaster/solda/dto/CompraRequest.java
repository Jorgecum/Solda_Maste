package com.soldaMaster.solda.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraRequest {
    private String tipoComprobante;
    private String serieCorrelativa;
    private LocalDateTime fechaCompra;
    private BigDecimal montoTotal;
    private Integer idOrden;    
    private Integer idProveedor;
    private Integer idUsuario;
}
