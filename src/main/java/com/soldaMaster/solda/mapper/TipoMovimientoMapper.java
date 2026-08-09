package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.entity.TiposMovimiento;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.TipoMovimientoRepository;

@Mapper(componentModel = "spring")
public abstract class TipoMovimientoMapper {

    @Autowired
    protected TipoMovimientoRepository repository;

    public TiposMovimiento map(Integer id){
        return repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException(id + " Tipo de movimiento no encotrado"));
    }
}
