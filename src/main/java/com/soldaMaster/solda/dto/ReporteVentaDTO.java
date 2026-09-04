package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReporteVentaDTO {
    private Page<VentaResponse> pagina;
    private BigDecimal totalRango;
    private BigDecimal totalGlobal;
    private Long cantidadGlobal;
    private BigDecimal ventasHoy;
    private BigDecimal ventasMes;
}
