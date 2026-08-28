package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.PagoRequest;
import com.soldaMaster.solda.dto.PagoResponse;
import com.soldaMaster.solda.entity.Pagos;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.PagoRepository;

@Mapper(componentModel = "spring", uses = {VentaMapper.class, MetodoPagoMapper.class})
public abstract class PagoMapper {
    
    @Autowired
    protected PagoRepository repository;

    @Mapping(target = "idPago", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "detallePagosList", ignore = true)
    public abstract Pagos toEntity(PagoRequest request);

    public abstract PagoResponse toResponse(Pagos pago);

    public abstract List<PagoResponse> toResponseList(List<Pagos> listaPagos);

    public Pagos map(Integer idPago){
        return repository.findById(idPago)
            .orElseThrow(()-> new RecursoNoEncontradoException(idPago + " Pago no encontrado"));
    }
}
