package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.TipoEntidadRequest;
import com.soldaMaster.solda.dto.TipoEntidadResponse;
import com.soldaMaster.solda.entity.TiposEntidad;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.TipoEntidadRepository;

@Mapper(componentModel = "spring")
public abstract class TipoEntidadMapper {

    @Autowired
    protected TipoEntidadRepository tipoEntidadRepository;

    @Mapping(target = "idTipoEntidad", ignore = true)
    public abstract TiposEntidad toEntity(TipoEntidadRequest request);

    public abstract TipoEntidadResponse toResponse(TiposEntidad tipoEntidad);

    public TiposEntidad map(Integer idTipoEntidad) {
        return tipoEntidadRepository.findById(idTipoEntidad).orElseThrow(()
            -> new RecursoNoEncontradoException(idTipoEntidad + " id Tipo Entidad no encontrado"));
    }
}