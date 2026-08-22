package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.MetodoPagoResponse;
import com.soldaMaster.solda.entity.MetodosPago;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.MetodoPagoRepository;

@Mapper(componentModel = "spring")
public abstract class MetodoPagoMapper {
    @Autowired
    protected MetodoPagoRepository repository;

    public abstract List<MetodoPagoResponse> toResponseList(List<MetodosPago> metodos);

    public MetodosPago map(Integer id){
        return repository.findById(id)
        .orElseThrow(()-> new RecursoNoEncontradoException(id + " Metodo de pago no encotrado"));
    }
}
