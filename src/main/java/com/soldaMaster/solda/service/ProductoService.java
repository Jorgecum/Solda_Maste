package com.soldaMaster.solda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.ActualizarStockRequest;
import com.soldaMaster.solda.dto.MovimientoInventarioRequest;
import com.soldaMaster.solda.dto.MovimientoInventarioResponse;
import com.soldaMaster.solda.dto.ProductoRequest;
import com.soldaMaster.solda.dto.ProductoResponse;
import com.soldaMaster.solda.entity.Productos;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.mapper.ProductoMapper;
import com.soldaMaster.solda.repository.ProductoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repository;
    private final LoteService loteService;
    private final ProductoMapper mapper;
    private final MovimientoInventarioService mService;

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

    @Transactional
    public void actualizarStock(ActualizarStockRequest productoStock ){
        Productos encontrado = repository.findById(productoStock.getIdProducto())
            .orElseThrow(()-> new RecursoNoEncontradoException(productoStock.getIdProducto() + " Producto no encontrado"));

        int cantidad = productoStock.getCantidad();

        MovimientoInventarioRequest movimiento = new MovimientoInventarioRequest();
        if(productoStock.getIdTipoMovimiento() == 1){

            cantidad = encontrado.getStock() + cantidad;

            movimiento.setCantidad(productoStock.getCantidad());
            movimiento.setIdLote(productoStock.getIdLote());
            movimiento.setIdProducto(encontrado.getIdProducto());
            movimiento.setIdTipoMovimiento(productoStock.getIdTipoMovimiento());
            movimiento.setReferencia(productoStock.getReferencia());
            mService.crearMovimiento(movimiento);

        }else{
            
            if(cantidad > encontrado.getStock()){
                throw new IllegalArgumentException(
                    "Stock insuficiente para realizar la salida"
                );
            }

            if(encontrado.getManejaLote()){
                loteService.actualizarStockLote(productoStock);
            }else{
                movimiento.setCantidad(productoStock.getCantidad());
                movimiento.setIdLote(null);
                movimiento.setIdProducto(encontrado.getIdProducto());
                movimiento.setIdTipoMovimiento(productoStock.getIdTipoMovimiento());
                movimiento.setReferencia(productoStock.getReferencia());
                mService.crearMovimiento(movimiento);
            }
            
            cantidad = encontrado.getStock() - cantidad;
        }
        encontrado.setStock(cantidad);
        repository.save(encontrado);
    }

    
}
