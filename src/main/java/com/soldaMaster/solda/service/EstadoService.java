package com.soldaMaster.solda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.EstadoRequest;
import com.soldaMaster.solda.dto.EstadoResponse;
import com.soldaMaster.solda.entity.EstadosSistema;
import com.soldaMaster.solda.mapper.EstadoMapper;
import com.soldaMaster.solda.repository.EstadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository repository;
    private final EstadoMapper mapper;

    public List<EstadoResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public EstadoResponse obtenerPorId(Integer id) {
        EstadosSistema estado = mapper.map(id);
        return mapper.toResponse(estado);
    }

    public EstadoResponse crear(EstadoRequest request) {
        EstadosSistema estado = mapper.toEntity(request);
        estado = repository.save(estado);
        return mapper.toResponse(estado);
    }

    public EstadoResponse actualizar(Integer id, EstadoRequest request) {
        EstadosSistema estado = mapper.map(id);

        estado.setTipoCodigo(request.getTipoCodigo());
        estado.setNombre(request.getNombre());

        estado = repository.save(estado);
        return mapper.toResponse(estado);
    }

    public void eliminar(Integer id) {
        EstadosSistema estado = mapper.map(id);
        repository.delete(estado);
    }
}