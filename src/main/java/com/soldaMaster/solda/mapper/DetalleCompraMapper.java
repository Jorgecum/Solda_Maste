package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.DetalleCompraRequest;
import com.soldaMaster.solda.dto.DetalleCompraResponse;
import com.soldaMaster.solda.entity.DetalleCompras;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.DetalleCompraRepository;

@Mapper(componentModel = "spring", uses = {CompraMapper.class, LoteMapper.class, ProductoMapper.class})
public abstract class DetalleCompraMapper {
    @Autowired DetalleCompraRepository repository;
    
    @Mapping(target = "idDetalleCompra", ignore = true)
    public abstract DetalleCompras toEntity(DetalleCompraRequest request);
    @Mapping(target = "idCompra", ignore = true)
    public abstract DetalleCompraResponse toResponse(DetalleCompras detalleCompra);
    public abstract List<DetalleCompraResponse> toResponseList(List<DetalleCompras> listaDetallesCompra);
    
    public DetalleCompras map(Integer idDetalle){
        return repository.findById(idDetalle)
            .orElseThrow(()-> new RecursoNoEncontradoException(idDetalle + " Detalle no encontrado"));
    }
}
