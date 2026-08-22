package com.soldaMaster.solda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.MetodoPagoResponse;
import com.soldaMaster.solda.mapper.MetodoPagoMapper;
import com.soldaMaster.solda.repository.MetodoPagoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetodoPagoService {
    private final MetodoPagoRepository repository;
    private final MetodoPagoMapper mapper;

    public List<MetodoPagoResponse> listarMetodosPago(){
        return mapper.toResponseList(repository.findAll());
    }
}
