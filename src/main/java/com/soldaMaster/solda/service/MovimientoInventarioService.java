package com.soldaMaster.solda.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.MovimientoInventarioRequest;
import com.soldaMaster.solda.dto.MovimientoInventarioResponse;
import com.soldaMaster.solda.entity.MovimientosInventario;
import com.soldaMaster.solda.mapper.MovimientoInventarioMapper;
import com.soldaMaster.solda.repository.MovimientoInventarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioService {
    private final MovimientoInventarioRepository repository;
    private final MovimientoInventarioMapper mapper;

    public MovimientoInventarioResponse crearMovimiento(MovimientoInventarioRequest request){
        MovimientosInventario guardar = mapper.toEntity(request);
        guardar = repository.save(guardar);
        return mapper.toResponse(guardar);
    }

    public List<MovimientoInventarioResponse> cardexGlobal(){
        List<MovimientosInventario> movimientos = repository.findTop100ByOrderByFechaDesc();        
        List<MovimientosInventario> filtrados = movimientos.stream()
            .filter(m -> m.getFecha() != null)
            .collect(Collectors.toList());

        return mapper.toResponseList(filtrados);
    }
    
    public List<MovimientoInventarioResponse> productoKardex(Integer id){
        return mapper.toResponseList(repository.findByIdProducto_IdProducto(id));
    }

    public List<MovimientoInventarioResponse> loteKardex(Integer id){
        return mapper.toResponseList(repository.findByIdLote_IdLote(id));
    }
}
