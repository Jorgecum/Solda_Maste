package com.soldaMaster.solda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.DetalleOrdenRequest;
import com.soldaMaster.solda.dto.DetalleOrdenResponse;
import com.soldaMaster.solda.entity.DetalleOrden;
import com.soldaMaster.solda.mapper.DetalleOrdenMapper;
import com.soldaMaster.solda.repository.DetalleOrdenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleOrdenService {
    private final DetalleOrdenRepository repository;
    private final DetalleOrdenMapper mapper;

    public List<DetalleOrdenResponse> crearDetalle(List<DetalleOrdenRequest> listDetalleOrdens, Integer idOrden){
        List<DetalleOrdenResponse> detalleOrdenResponses = new ArrayList<>();

        for(DetalleOrdenRequest detalle : listDetalleOrdens){
            detalle.setIdOrden(idOrden);
            DetalleOrden agregar = mapper.toEntity(detalle);
            agregar = repository.save(agregar);

            detalleOrdenResponses.add(mapper.toResponse(agregar));
        }

        return detalleOrdenResponses;
    }

    public List<DetalleOrdenResponse> detalleOrden(Integer idOrden){
        return mapper.toResponseList(repository.findByIdOrden_IdOrden(idOrden));
    }

    
}
