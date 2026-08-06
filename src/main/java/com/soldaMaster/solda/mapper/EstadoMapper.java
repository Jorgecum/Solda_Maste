package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
    public abstract EstadosSistema toEntity(EstadoRequest request);

    public abstract EstadoResponse toResponse(EstadosSistema estado);

    public EstadosSistema map(Integer idEstado) {
        return estadoRepository.findById(idEstado).orElseThrow(()
            -> new RecursoNoEncontradoException(idEstado + " id Estado no encontrado"));
    }

}