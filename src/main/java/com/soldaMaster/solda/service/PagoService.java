package com.soldaMaster.solda.service;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.PagoRequest;
import com.soldaMaster.solda.dto.PagoResponse;
import com.soldaMaster.solda.entity.Pagos;
import com.soldaMaster.solda.mapper.PagoMapper;
import com.soldaMaster.solda.repository.PagoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository repository;
    private final PagoMapper mapper;

    public PagoResponse crearPago(PagoRequest request){
        Pagos ingresar = mapper.toEntity(request);

        ingresar = repository.save(ingresar);

        return mapper.toResponse(ingresar);
    }

    public BigDecimal sumarPago(List<PagoRequest> listaPagos){
        if(listaPagos == null || listaPagos.isEmpty()){
            return BigDecimal.ZERO;
        }

        BigDecimal totalPagos = BigDecimal.ZERO;

        for(PagoRequest pago : listaPagos){
            totalPagos = totalPagos.add(pago.getMontoTotal());
        }

        return totalPagos;
    }
}
