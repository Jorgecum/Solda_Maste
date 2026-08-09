package com.soldaMaster.solda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.ProductoRequest;
import com.soldaMaster.solda.dto.ProductoResponse;
import com.soldaMaster.solda.entity.Productos;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.mapper.ProductoMapper;
import com.soldaMaster.solda.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    public ProductoResponse crearProducto(ProductoRequest request){
        Productos registrar = mapper.toEntity(request);
        registrar = repository.save(registrar);

        return mapper.toResponse(registrar);
    }

    public List<ProductoResponse> mostrarProductos(){
        List<Productos> productos = repository.findAll();
        List<ProductoResponse> listaProductos = new ArrayList<>();

        for(Productos producto : productos){
            ProductoResponse agregar = mapper.toResponse(producto);
            listaProductos.add(agregar);
        }

        return listaProductos;

    }

    public ProductoResponse obtenerProducto(Integer id){
        Productos encontrado = repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException(id+ " Producto no encontrado"));
            
        return mapper.toResponse(encontrado);
    }

    public ProductoResponse actualizarProducto(Integer id, ProductoRequest request){
        Productos encontrado = repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException(id+ " Producto no encontrado"));
        
        mapper.actualizarProducto(encontrado, request);

        encontrado = repository.save(encontrado);

        return mapper.toResponse(encontrado);
    }

    
}
