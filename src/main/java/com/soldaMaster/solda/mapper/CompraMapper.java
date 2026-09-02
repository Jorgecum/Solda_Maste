package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.CompraRequest;
import com.soldaMaster.solda.dto.CompraResponse;
import com.soldaMaster.solda.entity.Compras;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.CompraRepository;

@Mapper(componentModel = "spring", uses = {OrdenCompraMapper.class, EntidadMapper.class, UsuarioMapper.class, DetalleCompraMapper.class} )
public abstract class CompraMapper {
    @Autowired
    protected CompraRepository repository;
    @Mapping(target = "idCompra", ignore = true)
    @Mapping(target = "detalleComprasList", ignore = true)
    public abstract Compras toEntity(CompraRequest request);

    public abstract CompraResponse toResponse(Compras compra);
    public abstract List<CompraResponse> toResponseList(List<Compras> listaCompra);

    public Compras map(Integer idCompra){
        return repository.findById(idCompra)
            .orElseThrow(()-> new RecursoNoEncontradoException(idCompra + " Compra no encontrada"));
    }
}
