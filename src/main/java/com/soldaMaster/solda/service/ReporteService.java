package com.soldaMaster.solda.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.ReporteCompraDTO;
import com.soldaMaster.solda.dto.ReporteVentaDTO;
import com.soldaMaster.solda.entity.Compras;
import com.soldaMaster.solda.entity.Ventas;
import com.soldaMaster.solda.mapper.CompraMapper;
import com.soldaMaster.solda.mapper.VentaMapper;
import com.soldaMaster.solda.repository.CompraRepository;
import com.soldaMaster.solda.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteService {
    private final CompraRepository compraRepository;
    private final CompraMapper compraMapper;
    private final VentaMapper ventaMapper;
    private final VentaRepository rVentaRepository;

    public ReporteCompraDTO obtenerReporteCompra(int page, int size, Integer idProveedor, String fechaInicio, String fechaFin){
        LocalDateTime fInicio = (fechaInicio != null && !fechaInicio.isEmpty()) ? java.time.LocalDate.parse(fechaInicio).atTime(0, 0, 0) : null;
        LocalDateTime fFin = (fechaFin != null && !fechaFin.isEmpty()) ? java.time.LocalDate.parse(fechaFin).atTime(23, 59, 59) : null;

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Compras> paginaCompras = compraRepository.buscarParaReporte(idProveedor, fInicio, fFin, pageRequest);
        BigDecimal totalRango = compraRepository.sumarTotalReporte(idProveedor, fInicio, fFin);
        BigDecimal totalGlobal = compraRepository.sumarTotalReporte(null, null, null);
        long cantidadGlobal = compraRepository.count();

        return ReporteCompraDTO.builder()
                .pagina(paginaCompras.map(compraMapper::toResponse))
                .totalRango(totalRango)
                .totalGlobal(totalGlobal)
                .cantidadGlobal(cantidadGlobal)
                .build();
    }

    public ReporteVentaDTO obtenerReporteVenta(int page, int size, String fechaInicio, String fechaFin, String tipoComprobante, String clienteBusqueda) {
        if (page < 0) page = 0;
        
        java.time.LocalDateTime fInicio = (fechaInicio != null && !fechaInicio.isEmpty()) ? java.time.LocalDate.parse(fechaInicio).atTime(0, 0, 0) : null;
        java.time.LocalDateTime fFin = (fechaFin != null && !fechaFin.isEmpty()) ? java.time.LocalDate.parse(fechaFin).atTime(23, 59, 59) : null;
        
        String tComprobante = (tipoComprobante != null && !tipoComprobante.equals("Todos") && !tipoComprobante.isEmpty()) ? tipoComprobante : null;
        String cBusqueda = (clienteBusqueda != null && !clienteBusqueda.isEmpty()) ? clienteBusqueda : null;

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Ventas> paginaVentas = rVentaRepository.buscarParaReporte(fInicio, fFin, tComprobante, cBusqueda, pageRequest);
        
        return ReporteVentaDTO.builder()
                .pagina(paginaVentas.map(ventaMapper::toResponse)) 
                .totalRango(rVentaRepository.sumarTotalReporte(fInicio, fFin, tComprobante, cBusqueda))
                .totalGlobal(rVentaRepository.sumarTotalGlobal())
                .cantidadGlobal(rVentaRepository.count())
                .ventasHoy(rVentaRepository.sumarVentasHoy())
                .ventasMes(rVentaRepository.sumarVentasMes())
                .build();
    }
}
