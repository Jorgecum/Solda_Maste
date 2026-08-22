package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.soldaMaster.solda.dto.PagoRequest;
import com.soldaMaster.solda.dto.PagoResponse;
import com.soldaMaster.solda.entity.Pagos;

@Mapper(componentModel = "spring", uses = {VentaMapper.class, MetodoPagoMapper.class})
public abstract class PagoMapper {
    
    @Mapping(target = "idPago", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "detallePagosList", ignore = true)
    public abstract Pagos toEntity(PagoRequest request);

    public abstract PagoResponse toResponse(Pagos pago);
}
