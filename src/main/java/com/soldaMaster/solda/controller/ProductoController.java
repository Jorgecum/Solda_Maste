package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.ProductoRequest;
import com.soldaMaster.solda.dto.ProductoResponse;
import com.soldaMaster.solda.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public List<ProductoResponse> mostrarProductos(){
        return service.mostrarProductos();
    }

    @GetMapping("/{id}")
    public ProductoResponse obtenerProducto(@PathVariable Integer id){
        return service.obtenerProducto(id);
    }

    @PutMapping("/{id}")
    public ProductoResponse actualizarProducto(@PathVariable Integer id, @RequestBody ProductoRequest request){
        return service.actualizarProducto(id, request);
    }
    
    
}
