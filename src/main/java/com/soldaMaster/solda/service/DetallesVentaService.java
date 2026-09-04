package com.soldaMaster.solda.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.ActualizarStockRequest;
import com.soldaMaster.solda.dto.DetalleVentaRequest;
import com.soldaMaster.solda.dto.DetalleVentaResponse;
import com.soldaMaster.solda.dto.ProductoResponse;
import com.soldaMaster.solda.entity.DetalleVentas;
import com.soldaMaster.solda.mapper.DetallesVentaMapper;
import com.soldaMaster.solda.repository.DetallesVentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetallesVentaService {
    private final DetallesVentaRepository repository;
    private final ProductoService productoService;
    private final DetallesVentaMapper mapper;

    public DetalleVentaResponse crearDetalleVenta(DetalleVentaRequest request, String referenciaV){
        DetalleVentas ingresado = mapper.toEntity(request);
        ingresado = repository.save(ingresado);

        ActualizarStockRequest datosProducto = new ActualizarStockRequest();
        datosProducto.setIdProducto(request.getIdProducto());
        datosProducto.setCantidad(request.getCantidad());
        datosProducto.setIdLote(request.getIdLote());
        datosProducto.setIdTipoMovimiento(2);
        datosProducto.setReferencia(referenciaV);

        productoService.actualizarStock(datosProducto);
        return mapper.toResponse(ingresado);
    }


    public Boolean verificarMontoDetalle(DetalleVentaRequest request){
        ProductoResponse producto = productoService.obtenerProducto(request.getIdProducto());
        BigDecimal precio = request.getPrecioUnitario();
        int cantidad = request.getCantidad();
        
        if (producto.getStock() < cantidad) {
            System.out.println("Rechazado: Stock insuficiente para producto " + request.getIdProducto());
            return false;
        }

        boolean precioValido = false;
        
        if (producto.getPrecioVenta() != null && producto.getPrecioVenta().compareTo(precio) == 0) {
            precioValido = true;
        } else if (producto.getPrecioMayorista() != null && producto.getPrecioMayorista().compareTo(precio) == 0) {
            precioValido = true;
        } else if (producto.getPrecioDistribuidor() != null && producto.getPrecioDistribuidor().compareTo(precio) == 0) {
            precioValido = true;
        }

        if (!precioValido) {
            System.out.println("Rechazado: El precio " + precio + " no existe en el catálogo para el producto " + request.getIdProducto());
            return false;
        }

        BigDecimal descuento = request.getDescuentoProducto();
        if (descuento == null) {
            descuento = BigDecimal.ZERO;
        }

        BigDecimal subTotalCalculado = precio.multiply(BigDecimal.valueOf(cantidad)).subtract(descuento);

        BigDecimal diferencia = subTotalCalculado.subtract(request.getSubTotal()).abs();
        
        if (diferencia.compareTo(new BigDecimal("0.05")) > 0) {
            System.out.println("Rechazado: Subtotal descuadrado. Backend calculó " + subTotalCalculado + " pero Frontend envió " + request.getSubTotal());
            return false;
        }

        return true;
    }

    public List<DetalleVentaResponse> obtenerDetalleVenta(Integer idVenta){
        List<DetalleVentas> listaObtenida = repository.findByIdVenta_IdVenta(idVenta);

        return mapper.toResponseList(listaObtenida);
    }
}
