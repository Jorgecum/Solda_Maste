package com.soldaMaster.solda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.ActualizarStockRequest;
import com.soldaMaster.solda.dto.DetalleCompraRequest;
import com.soldaMaster.solda.dto.DetalleCompraResponse;
import com.soldaMaster.solda.dto.LoteRequest;
import com.soldaMaster.solda.dto.LoteResponse;
import com.soldaMaster.solda.entity.DetalleCompras;
import com.soldaMaster.solda.mapper.DetalleCompraMapper;
import com.soldaMaster.solda.repository.DetalleCompraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleCompraService {
    private final DetalleCompraRepository repository;
    private final DetalleCompraMapper mapper;
    private final ProductoService pService;
    private final LoteService loteService;

    public List<DetalleCompraResponse> crearDetalle(List<DetalleCompraRequest> listaDetalleCom, Integer idCompra, String serieCorrelativa){
        List<DetalleCompraResponse> listaDetalles = new ArrayList<>();

        for(DetalleCompraRequest detalle : listaDetalleCom){
            detalle.setIdCompra(idCompra);
            
            if(detalle.getNumeroLote() != null  && !detalle.getNumeroLote().trim().isEmpty()){
                LoteRequest loteCrear = new LoteRequest();
                loteCrear.setFechaEntrada(java.time.LocalDateTime.now(java.time.ZoneId.of("America/Lima")));

                loteCrear.setIdCertificado(null);
                loteCrear.setIdProducto(detalle.getIdProducto());
                loteCrear.setNumeroLote(detalle.getNumeroLote());
                loteCrear.setStockLote(detalle.getCantidad());

                LoteResponse loteCreado = loteService.crearLote(loteCrear);
                detalle.setIdLote(loteCreado.getIdLote());
            }else{
                detalle.setIdLote(null);
            }



            DetalleCompras agregarDetalle = mapper.toEntity(detalle);
            agregarDetalle = repository.save(agregarDetalle);

            DetalleCompraResponse datos = mapper.toResponse(agregarDetalle);
            ActualizarStockRequest datosStock = new ActualizarStockRequest();

            datosStock.setCantidad(datos.getCantidad());

            if (datos.getIdLote() != null) {
                datosStock.setIdLote(datos.getIdLote().getIdLote());
            } else {
                datosStock.setIdLote(null);
            }

            datosStock.setIdProducto(datos.getIdProducto().getIdProducto());
            datosStock.setIdTipoMovimiento(1);

            datosStock.setReferencia("COMPRA: " + serieCorrelativa);

            pService.actualizarStock(datosStock);

            listaDetalles.add(mapper.toResponse(agregarDetalle));
        }

        return listaDetalles;
    }

    public List<DetalleCompraResponse> obtenerDetalleCompra(Integer idCompra){
        return mapper.toResponseList(repository.findByIdCompra_IdCompra(idCompra));
    }
}
