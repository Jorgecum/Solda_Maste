package com.soldaMaster.solda.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soldaMaster.solda.dto.CertificadoRequest;
import com.soldaMaster.solda.dto.CertificadoResponse;
import com.soldaMaster.solda.dto.LoteRequest;
import com.soldaMaster.solda.dto.LoteResponse;
import com.soldaMaster.solda.dto.MovimientoInventarioRequest;
import com.soldaMaster.solda.dto.MovimientoInventarioResponse;
import com.soldaMaster.solda.dto.ProductoRequest;
import com.soldaMaster.solda.dto.ProductoResponse;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final ProductoService productoService;
    private final MovimientoInventarioService movimientoService;
    private final LoteService loteService;
    private final CertificadoService certificadoService;
    
    @Transactional
    public MovimientoInventarioResponse ingresarProducto(ProductoRequest request, LoteRequest loteRequest, CertificadoRequest certificadoRequest ){
        ProductoResponse ingresado = productoService.crearProducto(request);
        MovimientoInventarioRequest movimiento = new MovimientoInventarioRequest();

        if(ingresado.getManejaLote()){

            if(certificadoRequest != null){
                CertificadoResponse certificadoLote = certificadoService.crearCertificado(certificadoRequest);
                loteRequest.setIdCertificado(certificadoLote.getIdCertificado());
            }

            loteRequest.setIdProducto(ingresado.getIdProducto());
            LoteResponse loteProducto = loteService.crearLote(loteRequest);

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
}
