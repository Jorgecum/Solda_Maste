package com.soldaMaster.solda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.ActualizarStockRequest;
import com.soldaMaster.solda.dto.LoteRequest;
import com.soldaMaster.solda.dto.LoteResponse;
import com.soldaMaster.solda.dto.MovimientoInventarioRequest;
import com.soldaMaster.solda.entity.Certificados;
import com.soldaMaster.solda.entity.Lotes;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.mapper.LoteMapper;
import com.soldaMaster.solda.repository.LoteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoteService {
    private final LoteRepository repository;
    private final CertificadoService certificadoService;
    private final MovimientoInventarioService mService;
    private final LoteMapper mapper;

    public LoteResponse crearLote(LoteRequest request){
        Lotes ingresar = mapper.toEntity(request);
        ingresar = repository.save(ingresar);
        return mapper.toResponse(ingresar);
    }

    public List<LoteResponse> mostrarLotesProd(Integer id){
        return mapper.toResponseList(repository.findByIdProducto_IdProducto(id));
    }

    public LoteResponse subirCertificado(Integer id, Integer idCertificado){
        Lotes encontrado = repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException(id +" Lote no encontrado"));
        Certificados certificado = certificadoService.obtenerCertificado(idCertificado);

        encontrado.setIdCertificado(certificado);

        encontrado = repository.save(encontrado);

        return mapper.toResponse(encontrado);
    }

    @Transactional
    public void actualizarStockLote(ActualizarStockRequest datos){
        List<Lotes> listaLotes = repository.findByIdProducto_IdProductoOrderByFechaEntradaAsc(datos.getIdProducto());
        int cantidad = datos.getCantidad();

        for(Lotes lote : listaLotes){

            if (cantidad <= 0) {
                break;
            }

            int cantidaLote = lote.getStockLote();

            if (cantidaLote <= 0) {
                continue;
            }
            
            int cantidadMovimiento;

            if (cantidaLote >= cantidad) {

                cantidadMovimiento = cantidad;
                lote.setStockLote(cantidaLote - cantidad);
                cantidad = 0;

            } else {

                cantidadMovimiento = cantidaLote;

                cantidad -= cantidaLote;
                lote.setStockLote(0);
            }

            repository.save(lote);
            
            MovimientoInventarioRequest movimiento = new MovimientoInventarioRequest();
            movimiento.setCantidad(cantidadMovimiento);
            movimiento.setIdLote(lote.getIdLote());
            movimiento.setIdProducto(datos.getIdProducto());
            movimiento.setIdTipoMovimiento(datos.getIdTipoMovimiento());
            movimiento.setReferencia(datos.getReferencia());

            
            mService.crearMovimiento(movimiento);
        }

        if (cantidad > 0) {
            throw new RuntimeException(
                "No hay suficiente stock en los lotes"
            );
        }
    }

    
}
