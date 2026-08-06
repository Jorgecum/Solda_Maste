package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.EntidadRequest;
import com.soldaMaster.solda.dto.EntidadResponse;
import com.soldaMaster.solda.entity.Entidades;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.EntidadRepository;

@Mapper(componentModel = "spring", uses = { EstadoMapper.class, TipoEntidadMapper.class })
public abstract class EntidadMapper {

    @Autowired
    protected EntidadRepository entidadRepository;

    @Autowired
    protected EstadoMapper estadoMapper;

    @Autowired
    protected TipoEntidadMapper tipoEntidadMapper;

    @Mapping(target = "idEntidad", ignore = true)
    @Mapping(target = "idEstado", expression = "java(estadoMapper.map(request.getIdEstado()))")
    @Mapping(target = "idTipoEntidad", expression = "java(request.getIdTipoEntidad() != null ? tipoEntidadMapper.map(request.getIdTipoEntidad()) : null)")
    public abstract Entidades toEntity(EntidadRequest request);

    public abstract EntidadResponse toResponse(Entidades entidad);

    public Entidades map(Integer idEntidad) {
        return entidadRepository.findById(idEntidad).orElseThrow(()
            -> new RecursoNoEncontradoException(idEntidad + " id Entidad no encontrado"));
    }
}