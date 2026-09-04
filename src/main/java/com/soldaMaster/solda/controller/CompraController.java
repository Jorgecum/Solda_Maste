package com.soldaMaster.solda.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.CompraRequest;
import com.soldaMaster.solda.dto.CompraResponse;
import com.soldaMaster.solda.service.CompraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/compra")
@RequiredArgsConstructor
public class CompraController {
    private final CompraService service;


    @PostMapping()
    public CompraResponse crearCompra(@RequestBody CompraRequest request){
        return service.crearCompra(request);
    }

    @GetMapping("/{idCompra}")
    public CompraResponse obtenerDetalleCompra(@PathVariable Integer idCompra){
        return service.obtenerCompra(idCompra);
    }

    @GetMapping("/global")
    public Page<CompraResponse> listarComprasGlobal(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {
        return service.listarComprasPaginadas(page, size, search);
    }

    @GetMapping("/proveedor/{idProveedor}")
    public Page<CompraResponse> listarCompraProveedor(
            @PathVariable Integer idProveedor,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
            
        return service.listarCompraProveedor(idProveedor, page, size);
    }
}
