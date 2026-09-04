package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.MetodoPagoResponse;
import com.soldaMaster.solda.dto.VentaCreditoResponse;
import com.soldaMaster.solda.dto.VentaRequest;
import com.soldaMaster.solda.dto.VentaResponse;
import com.soldaMaster.solda.service.MetodoPagoService;
import com.soldaMaster.solda.service.VentaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/venta")
@RequiredArgsConstructor
public class VentaController {
    private final VentaService service;
    private final MetodoPagoService mPagoService;

    @PostMapping
    public VentaResponse crearVenta(@RequestBody VentaRequest request){
        return service.crearVenta(request);
    }

    @GetMapping("/metodo-pago")
    public List<MetodoPagoResponse> listarMetodosPago(){
        return mPagoService.listarMetodosPago();
    }

    @GetMapping("/credito")
    public List<VentaCreditoResponse> listaVentasCredito(){
        return service.ventasCredito();
    }

    @GetMapping()
    public List<VentaResponse> mostrarVentas(){
        return service.mostrarVentas();
    }

    @GetMapping("/{idVenta}")
    public VentaResponse obtenerDetalleVenta(@PathVariable Integer idVenta){
        return service.obtenerVentaConDetalle(idVenta);
    }

    @GetMapping("/cliente/{idCliente}")
    public Page<VentaResponse> listarVentasPorCliente(
            @PathVariable Integer idCliente,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
            
        return service.listarVentaCliente(idCliente, page, size);
    }
}
