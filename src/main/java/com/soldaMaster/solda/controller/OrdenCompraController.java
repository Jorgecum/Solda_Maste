package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.OrdenCompraRequest;
import com.soldaMaster.solda.dto.OrdenCompraResponse;
import com.soldaMaster.solda.service.OrdenCompraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orden-compra")
@RequiredArgsConstructor
public class OrdenCompraController {
    private final OrdenCompraService service;

    @PostMapping()
    public OrdenCompraResponse crearOrden(@RequestBody OrdenCompraRequest request){
        return service.crearOrden(request);
    }

    @GetMapping("/global")
    public Page<OrdenCompraResponse> listarGlobal(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String search) {
        return service.listarOrdenesPag(page, size, search);
    }

    @GetMapping("/{id}")
    public OrdenCompraResponse obtenerPorId(@PathVariable Integer id) {
        return service.obtenerOrdenCompra(id);
    }


    @GetMapping("/pendientes")
    public List<OrdenCompraResponse> listarPendientes() {
        return service.ordenesPendientes();
    }

    @PutMapping("/{id}/rechazar")
    public OrdenCompraResponse rechazarOrden(@PathVariable Integer id) {
        return service.rechazarOrdenCompra(id);
    }


}
