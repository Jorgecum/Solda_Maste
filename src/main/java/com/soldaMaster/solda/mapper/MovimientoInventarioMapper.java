package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.soldaMaster.solda.dto.MovimientoInventarioRequest;
import com.soldaMaster.solda.dto.MovimientoInventarioResponse;
import com.soldaMaster.solda.entity.MovimientosInventario;

@Mapper(componentModel =  "spring", uses = {ProductoMapper.class, TipoMovimientoMapper.class, LoteMapper.class})
public interface MovimientoInventarioMapper {
    @Mapping(target = "idMovimiento", ignore = true)
    MovimientosInventario toEntity(MovimientoInventarioRequest request);

    MovimientoInventarioResponse toResponse(MovimientosInventario movimiento);

    List<MovimientoInventarioResponse> toResponseList(List<MovimientosInventario> movimientos);
}
