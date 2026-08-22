package com.soldaMaster.solda.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.CuotaRequest;
import com.soldaMaster.solda.dto.CuotaResponse;
import com.soldaMaster.solda.entity.Cuotas;
import com.soldaMaster.solda.mapper.CuotaMapper;
import com.soldaMaster.solda.repository.CuotaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuotaService {
    private final CuotaRepository repository;
    private final CuotaMapper mapper;

    public CuotaResponse crearCuota(CuotaRequest request){
        Cuotas ingresar = mapper.toEntity(request);
        ingresar = repository.save(ingresar);

        return mapper.toResponse(ingresar);
    }

    public BigDecimal sumarCuota(List<CuotaRequest> listaCuotas){
        if(listaCuotas ==  null || listaCuotas.isEmpty()){
            return BigDecimal.ZERO;
        }

        BigDecimal sumaCuota = BigDecimal.ZERO;
        
        for(CuotaRequest cuota : listaCuotas){
            sumaCuota = sumaCuota.add(cuota.getMonto());
        }

        return sumaCuota;

    }
}
