package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ResumenDashboardDTO {
    private BigDecimal ventasHoy;
    private BigDecimal ventasMes;
    private BigDecimal comprasMes;
    private BigDecimal utilidadNeta;
    private Long totalClientes;
    private Long totalProductos;

    private Long inventarioUnidades;
    private BigDecimal inventarioValor;
    private Long alertasStockCritico;
}
