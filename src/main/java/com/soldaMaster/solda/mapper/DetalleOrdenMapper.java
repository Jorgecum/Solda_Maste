package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.DetalleOrdenRequest;
import com.soldaMaster.solda.dto.DetalleOrdenResponse;
import com.soldaMaster.solda.entity.DetalleOrden;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.DetalleOrdenRepository;

@Mapper(componentModel = "spring", uses = {OrdenCompraMapper.class, ProductoMapper.class})
public abstract class DetalleOrdenMapper {
    @Autowired
    protected DetalleOrdenRepository repository;

    @Mapping(target = "idDetalleOrden", ignore = true)
    public abstract DetalleOrden toEntity(DetalleOrdenRequest request);
    public abstract DetalleOrdenResponse toResponse(DetalleOrden detalleOrden);
    public abstract List<DetalleOrdenResponse> toResponseList(List<DetalleOrden> listaDetalles);

    public DetalleOrden map(Integer idDetalleOrden){
        return repository.findById(idDetalleOrden)
            .orElseThrow(()-> new RecursoNoEncontradoException(idDetalleOrden + " Detalle no encontrado"));
    }
}
