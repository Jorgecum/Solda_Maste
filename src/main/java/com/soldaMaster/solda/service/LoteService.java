package com.soldaMaster.solda.service;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.LoteRequest;
import com.soldaMaster.solda.dto.LoteResponse;
import com.soldaMaster.solda.entity.Lotes;
import com.soldaMaster.solda.mapper.LoteMapper;
import com.soldaMaster.solda.repository.LoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoteService {
    private final LoteRepository repository;
    private final LoteMapper mapper;

    public LoteResponse crearLote(LoteRequest request){
        Lotes ingresar = mapper.toEntity(request);
        ingresar = repository.save(ingresar);
        return mapper.toResponse(ingresar);
    }
}
