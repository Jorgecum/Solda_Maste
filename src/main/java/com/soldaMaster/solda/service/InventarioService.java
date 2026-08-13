package com.soldaMaster.solda.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soldaMaster.solda.dto.CertificadoRequest;
import com.soldaMaster.solda.dto.CertificadoResponse;
import com.soldaMaster.solda.dto.LoteRequest;
import com.soldaMaster.solda.dto.LoteResponse;
import com.soldaMaster.solda.dto.MovimientoInventarioRequest;
import com.soldaMaster.solda.dto.MovimientoInventarioResponse;
import com.soldaMaster.solda.dto.ProductoInventarioRequest;
import com.soldaMaster.solda.dto.ProductoResponse;
import com.soldaMaster.solda.entity.Certificados;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final ProductoService productoService;
    private final MovimientoInventarioService movimientoService;
    private final LoteService loteService;
    private final CertificadoService certificadoService;
    
    @Transactional
    public MovimientoInventarioResponse ingresarProducto(ProductoInventarioRequest request ){
        ProductoResponse ingresado = productoService.crearProducto(request.getProducto());
        MovimientoInventarioRequest movimiento = new MovimientoInventarioRequest();

        if(ingresado.getManejaLote()){

            LoteRequest lote = request.getLote();

            if(request.getCertificado() != null){
                CertificadoResponse certificadoLote = certificadoService.crearCertificado(request.getCertificado());
                lote.setIdCertificado(certificadoLote.getIdCertificado());
            }

            lote.setIdProducto(ingresado.getIdProducto());
            LoteResponse loteProducto = loteService.crearLote(lote);

            movimiento.setCantidad(loteProducto.getStockLote());
            movimiento.setReferencia("Saldo Inicial");
            movimiento.setIdLote(loteProducto.getIdLote());
            movimiento.setIdProducto(ingresado.getIdProducto());
            movimiento.setIdTipoMovimiento(1);

            

        }else{
            
            movimiento.setCantidad(ingresado.getStock());
            movimiento.setReferencia("Saldo Inicial");
            movimiento.setIdLote(null);
            movimiento.setIdProducto(ingresado.getIdProducto());
            movimiento.setIdTipoMovimiento(1);
        }

        return movimientoService.crearMovimiento(movimiento);

    }

    @Transactional
    public MovimientoInventarioResponse ingresarLote(LoteRequest request){
        LoteResponse lote = loteService.crearLote(request);

        Integer producto = lote.getIdProducto().getIdProducto();
        int stockIngreso = lote.getStockLote();

        productoService.actualizarStock(producto, 1, stockIngreso);

        MovimientoInventarioRequest movimientoLote = new MovimientoInventarioRequest();
        movimientoLote.setCantidad(stockIngreso);
        movimientoLote.setFecha(lote.getFechaEntrada());
        movimientoLote.setReferencia("Ingreso manual");
        movimientoLote.setIdLote(lote.getIdLote());
        movimientoLote.setIdProducto(producto);
        movimientoLote.setIdTipoMovimiento(1);

        return movimientoService.crearMovimiento(movimientoLote); 
    }

    @Transactional
    public LoteResponse subirCertificado(Integer idLote, CertificadoRequest request){
        CertificadoResponse certificadoIngresado = certificadoService.crearCertificado(request);

        return loteService.subirCertificado(idLote, certificadoIngresado.getIdCertificado());

    }
}
