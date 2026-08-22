package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.soldaMaster.solda.dto.CuotaRequest;
import com.soldaMaster.solda.dto.CuotaResponse;
import com.soldaMaster.solda.entity.Cuotas;

@Mapper(componentModel = "spring", uses = {EstadoMapper.class, VentaMapper.class})
public abstract class CuotaMapper {

    @Mapping(target = "idCuota", ignore = true)
    @Mapping(target = "detallePagosList", ignore = true)
    public abstract Cuotas toEntity(CuotaRequest request);

    public abstract CuotaResponse toResponse(Cuotas cuota);
}
