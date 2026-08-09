package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.LoteRequest;
import com.soldaMaster.solda.dto.LoteResponse;
import com.soldaMaster.solda.entity.Lotes;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.LoteRepository;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class, CertificadoMapper.class})
public abstract class LoteMapper {

    @Autowired
    protected LoteRepository repository;

    @Mapping(target = "idLote", ignore = true)
    @Mapping(target = "movimientosInventarioList", ignore = true)
    @Mapping(target = "detalleVentasList", ignore = true)
    @Mapping(target = "detalleNotaCreditoList", ignore = true)
    @Mapping(target = "detalleComprasList", ignore = true)

    public abstract Lotes toEntity(LoteRequest request);

    public abstract LoteResponse toResponse(Lotes lote);

    public Lotes map(Integer id){
        if(id == null){
            return null;
        }

        return  repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException(id + " Lote no encontrado"));
    }
}
