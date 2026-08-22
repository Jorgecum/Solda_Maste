package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.VentaRequest;
import com.soldaMaster.solda.dto.VentaResponse;
import com.soldaMaster.solda.entity.Ventas;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.VentaRepository;

@Mapper(componentModel = "spring", uses = {EntidadMapper.class, EstadoMapper.class, UsuarioMapper.class, DetallesVentaMapper.class} )
public abstract class VentaMapper {
    @Autowired
    protected VentaRepository repository;

    @Mapping(target = "idVenta", ignore = true)
    @Mapping(target = "cuotasList", ignore = true)
    @Mapping(target = "pagosList", ignore = true)
    @Mapping(target = "notasCreditoList", ignore = true)
    @Mapping(target = "detalleVentasList", ignore = true)
    public abstract Ventas toEntity (VentaRequest request);

    public abstract VentaResponse toResponse(Ventas venta);

    public Ventas map(Integer id){
        return repository.findById(id).orElseThrow(()
            -> new RecursoNoEncontradoException(id + " Venta no encontrado"));
    }
}
