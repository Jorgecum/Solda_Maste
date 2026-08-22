package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.DetalleVentaRequest;
import com.soldaMaster.solda.dto.DetalleVentaResponse;
import com.soldaMaster.solda.entity.DetalleVentas;
import com.soldaMaster.solda.repository.DetallesVentaRepository;

@Mapper(componentModel = "spring", uses = {LoteMapper.class, ProductoMapper.class, VentaMapper.class})
public abstract class DetallesVentaMapper {
    @Autowired
    protected DetallesVentaRepository repository;

    @Mapping(target = "idDetalle", ignore = true)
    public abstract DetalleVentas toEntity(DetalleVentaRequest request);

    public abstract DetalleVentaResponse toResponse(DetalleVentas detalleVentas);

    public abstract List<DetalleVentas> toEntityList(List<DetalleVentaRequest> list);
}
