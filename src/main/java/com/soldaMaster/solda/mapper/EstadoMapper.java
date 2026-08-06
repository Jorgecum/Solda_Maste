package com.soldaMaster.solda.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.EstadoRequest;
import com.soldaMaster.solda.dto.EstadoResponse;
import com.soldaMaster.solda.entity.EstadosSistema;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.EstadoRepository;

@Mapper(componentModel = "spring")
public abstract class EstadoMapper {

    @Autowired
    protected EstadoRepository estadoRepository;

    @Mapping(target = "idEstado", ignore = true)
    @Mapping(target = "medidasList", ignore = true)
    @Mapping(target = "categoriasList", ignore = true)
    @Mapping(target = "usuariosList", ignore = true)
    @Mapping(target = "cuotasList", ignore = true)
    @Mapping(target = "productosList", ignore = true)
    @Mapping(target = "ordenesCompraList", ignore = true)
    @Mapping(target = "entidadesList", ignore = true)
    @Mapping(target = "ventasList", ignore = true)
    public abstract EstadosSistema toEntity(EstadoRequest request);

    public abstract EstadoResponse toResponse(EstadosSistema estado);

    @InheritConfiguration(name = "toEntity")
    public abstract void actualizarEstado( @MappingTarget EstadosSistema actualizar, EstadoRequest datos);

    public EstadosSistema map(Integer idEstado) {
        return estadoRepository.findById(idEstado).orElseThrow(()
            -> new RecursoNoEncontradoException(idEstado + " id Estado no encontrado"));
    }

}