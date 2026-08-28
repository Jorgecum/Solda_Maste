package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.soldaMaster.solda.dto.DetallePagoRequest;
import com.soldaMaster.solda.dto.DetallePagoResponse;
import com.soldaMaster.solda.entity.DetallePagos;

@Mapper(componentModel = "spring", uses = {PagoMapper.class, CuotaMapper.class})
public abstract class DetallePagoMapper {

    @Mapping(target = "idDetallePago", ignore = true)
    public abstract DetallePagos toEntity(DetallePagoRequest request);
    public abstract DetallePagoResponse toResponse(DetallePagos pago);

    public abstract List<DetallePagoResponse> toResposeList(List<DetallePagos> pagos);
}
