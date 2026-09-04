package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/todos")
    public List<ProductoResponse> mostrarTodosLosProductos(){
        return service.mostrarProductos();
    }

    @GetMapping
    public Page<ProductoResponse> mostrarProductosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String busqueda,
            @RequestParam(required = false) Integer idCategoria) {

        return service.listarPaginado(page, size, busqueda, idCategoria);
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
