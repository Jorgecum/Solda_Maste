package com.soldaMaster.solda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.TipoEntidadRequest;
import com.soldaMaster.solda.dto.TipoEntidadResponse;
import com.soldaMaster.solda.entity.TiposEntidad;
import com.soldaMaster.solda.mapper.TipoEntidadMapper;
import com.soldaMaster.solda.repository.TipoEntidadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoEntidadService {

    private final TipoEntidadRepository repository;
    private final TipoEntidadMapper mapper;

    public List<TipoEntidadResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public TipoEntidadResponse obtenerPorId(Integer id) {
        TiposEntidad tipoEntidad = mapper.map(id);
        return mapper.toResponse(tipoEntidad);
    }

    public TipoEntidadResponse crear(TipoEntidadRequest request) {
        TiposEntidad tipoEntidad = mapper.toEntity(request);
        tipoEntidad = repository.save(tipoEntidad);
        return mapper.toResponse(tipoEntidad);
    }

    public TipoEntidadResponse actualizar(Integer id, TipoEntidadRequest request) {
        TiposEntidad tipoEntidad = mapper.map(id);
        tipoEntidad.setNombre(request.getNombre());
        tipoEntidad = repository.save(tipoEntidad);
        return mapper.toResponse(tipoEntidad);
    }

    public void eliminar(Integer id) {
        TiposEntidad tipoEntidad = mapper.map(id);
        repository.delete(tipoEntidad);
    }
}