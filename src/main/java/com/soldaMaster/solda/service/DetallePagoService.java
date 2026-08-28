package com.soldaMaster.solda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.DetallePagoRequest;
import com.soldaMaster.solda.dto.DetallePagoResponse;
import com.soldaMaster.solda.entity.DetallePagos;
import com.soldaMaster.solda.mapper.DetallePagoMapper;
import com.soldaMaster.solda.repository.DetallePagoRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetallePagoService {
    private final DetallePagoRespository respository;
    private final DetallePagoMapper mapper;

    public DetallePagoResponse crearDetallePago(DetallePagoRequest request){
        DetallePagos ingresado = mapper.toEntity(request);

        ingresado = respository.save(ingresado);

        return mapper.toResponse(ingresado);
    }

    public List<DetallePagoResponse> listaPagoCuota(Integer idCuota){
        return mapper.toResposeList(respository.findByIdCuota_IdCuota(idCuota));
    }
    
}
