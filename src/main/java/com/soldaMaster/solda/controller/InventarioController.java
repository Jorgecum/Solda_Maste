package com.soldaMaster.solda.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.CertificadoRequest;
import com.soldaMaster.solda.dto.LoteRequest;
import com.soldaMaster.solda.dto.LoteResponse;
import com.soldaMaster.solda.dto.MovimientoInventarioResponse;
import com.soldaMaster.solda.dto.ProductoInventarioRequest;
import com.soldaMaster.solda.service.InventarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventario")
@RequiredArgsConstructor
public class InventarioController {
    private final InventarioService service;
    
    @PostMapping("/producto")
    public MovimientoInventarioResponse crearProducto(@RequestBody ProductoInventarioRequest request){
        return service.ingresarProducto(request);
    }
    
    @PostMapping("/lote")
    public MovimientoInventarioResponse ingresarLote(@RequestBody LoteRequest request){
        return service.ingresarLote(request);
    }

    @PostMapping("/certificado/{id}")
    public LoteResponse subirCertificado(@PathVariable Integer id, @RequestBody CertificadoRequest request ){
        return service.subirCertificado(id, request);
    }
    
}
