package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.CertificadoRequest;
import com.soldaMaster.solda.dto.CertificadoResponse;
import com.soldaMaster.solda.entity.Certificados;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.CertificadoRepository;

@Mapper(componentModel = "spring")
public abstract class CertificadoMapper {
    @Autowired
    protected CertificadoRepository repository;

    @Mapping(target = "idCertificado", ignore = true)
    @Mapping(target = "lotesList", ignore = true)
    public abstract Certificados toEntity(CertificadoRequest request);
    
    public abstract CertificadoResponse toResponse(Certificados certificado);
    
    public Certificados map(Integer id){
        if(id == null){
            return null;
        }

        return repository.findById(id)
            .orElseThrow(()->new RecursoNoEncontradoException(id + " Certificado no encontrado"));
    }
}
