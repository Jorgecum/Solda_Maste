package com.soldaMaster.solda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.LoteRequest;
import com.soldaMaster.solda.dto.LoteResponse;
import com.soldaMaster.solda.entity.Certificados;
import com.soldaMaster.solda.entity.Lotes;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.mapper.LoteMapper;
import com.soldaMaster.solda.repository.LoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoteService {
    private final LoteRepository repository;
    private final CertificadoService certificadoService;
    private final LoteMapper mapper;

    public LoteResponse crearLote(LoteRequest request){
        Lotes ingresar = mapper.toEntity(request);
        ingresar = repository.save(ingresar);
        return mapper.toResponse(ingresar);
    }

    public List<LoteResponse> mostrarLotesProd(Integer id){
        return mapper.toResponseList(repository.findByIdProducto_IdProducto(id));
    }

    public LoteResponse subirCertificado(Integer id, Integer idCertificado){
        Lotes encontrado = repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException(id +" Lote no encontrado"));
        Certificados certificado = certificadoService.obtenerCertificado(idCertificado);

        encontrado.setIdCertificado(certificado);

        encontrado = repository.save(encontrado);

        return mapper.toResponse(encontrado);
    }
}
