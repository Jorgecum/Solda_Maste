package com.soldaMaster.solda.service;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.CertificadoRequest;
import com.soldaMaster.solda.dto.CertificadoResponse;
import com.soldaMaster.solda.entity.Certificados;
import com.soldaMaster.solda.mapper.CertificadoMapper;
import com.soldaMaster.solda.repository.CertificadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificadoService {

    private final CertificadoRepository repository;
    private final CertificadoMapper mapper;

    public CertificadoResponse crearCertificado(CertificadoRequest request){
        Certificados ingresar = mapper.toEntity(request);
        ingresar = repository.save(ingresar);
        return mapper.toResponse(ingresar);
    }
}
