package com.soldaMaster.solda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.ReporteCompraDTO;
import com.soldaMaster.solda.dto.ReporteVentaDTO;
import com.soldaMaster.solda.service.ReporteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reporte")
@RequiredArgsConstructor
public class ReportesController {

    private final ReporteService service;

    @GetMapping("/compras")
    public ReporteCompraDTO reporteCompras(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) Integer proveedor,
        @RequestParam(required = false) String dateStart,
        @RequestParam(required = false) String dateEnd) {
        
        return service.obtenerReporteCompra(page, size, proveedor, dateStart, dateEnd);
    }

    @GetMapping("/ventas")
    public ReporteVentaDTO reporteVentas(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String dateStart,
        @RequestParam(required = false) String dateEnd,
        @RequestParam(required = false) String docType,
        @RequestParam(required = false) String client) { 
        
        return service.obtenerReporteVenta(page, size, dateStart, dateEnd, docType, client);
    }
}
