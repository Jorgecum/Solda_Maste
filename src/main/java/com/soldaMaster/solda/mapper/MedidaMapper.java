package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.MedidaResponse;
import com.soldaMaster.solda.entity.Medidas;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.MedidaRepository;

@Mapper(componentModel = "spring")
public abstract class MedidaMapper {
    @Autowired
    protected MedidaRepository repository;
    public abstract MedidaResponse toResponse(Medidas medida);

    public Medidas map(Integer id){
        return repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException(id+ " Medida no encotrada"));
    }
}
