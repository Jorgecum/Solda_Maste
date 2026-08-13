package com.soldaMaster.solda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.MedidaResponse;
import com.soldaMaster.solda.mapper.MedidaMapper;
import com.soldaMaster.solda.repository.MedidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedidaService {
    private final MedidaRepository repository;
    private final MedidaMapper mapper;

    public List<MedidaResponse> mostrarMedidas(){
        return mapper.toResponseList(repository.findAll());
    }
}
