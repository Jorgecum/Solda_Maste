package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.OrdenCompraRequest;
import com.soldaMaster.solda.dto.OrdenCompraResponse;
import com.soldaMaster.solda.entity.OrdenesCompra;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.OrdenCompraRepository;

@Mapper(componentModel = "spring", uses = {EntidadMapper.class, EstadoMapper.class, UsuarioMapper.class} )
public abstract class OrdenCompraMapper {
    @Autowired
    protected OrdenCompraRepository repository;

    @Mapping(target = "idOrden", ignore = true)
    @Mapping(target = "comprasList", ignore = true)
    @Mapping(target = "detalleOrdenList", ignore = true)
    public abstract OrdenesCompra toEntity(OrdenCompraRequest request);
    public abstract OrdenCompraResponse toResponse(OrdenesCompra ordenCompra);
    public abstract List<OrdenCompraResponse> toResponseList(List<OrdenesCompra> listaOrdenes);

    public OrdenesCompra map(Integer idOrden){
        return repository.findById(idOrden)
            .orElseThrow(()-> new RecursoNoEncontradoException(idOrden + " Orden no encontrada"));
    }
}
