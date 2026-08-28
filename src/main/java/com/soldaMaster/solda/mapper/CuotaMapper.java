package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.CuotaRequest;
import com.soldaMaster.solda.dto.CuotaResponse;
import com.soldaMaster.solda.entity.Cuotas;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.CuotaRepository;

@Mapper(componentModel = "spring", uses = {EstadoMapper.class, VentaMapper.class})
public abstract class CuotaMapper {

    @Autowired
    protected CuotaRepository repository;

    @Mapping(target = "idCuota", ignore = true)
    @Mapping(target = "detallePagosList", ignore = true)
    public abstract Cuotas toEntity(CuotaRequest request);

    @Mapping(target = "montoPagado", ignore = true)
    @Mapping(target = "montoPendiente", ignore = true)
    public abstract CuotaResponse toResponse(Cuotas cuota);

    public abstract List<CuotaResponse> toResponseList(List<Cuotas> cuotas);

    public Cuotas map(Integer idCuota){
        return repository.findById(idCuota)
            .orElseThrow(()-> new RecursoNoEncontradoException(idCuota + " Cuota no encotrada"));
    }
}
