package com.soldaMaster.solda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.EstadoRequest;
import com.soldaMaster.solda.dto.EstadoResponse;
import com.soldaMaster.solda.entity.EstadosSistema;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
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
        EstadosSistema estado = repository.findById(id).orElseThrow(()
            -> new RecursoNoEncontradoException(id + " id Estado no encontrado"));

        return mapper.toResponse(estado);
    }

    public EstadosSistema obtenerPorIdSistema(Integer id) {
        return repository.findById(id).orElseThrow(()
            -> new RecursoNoEncontradoException(id + " id Estado no encontrado"));
    }

    public EstadoResponse crear(EstadoRequest request) {
        EstadosSistema estado = mapper.toEntity(request);
        estado = repository.save(estado);
        return mapper.toResponse(estado);
    }

    public EstadoResponse actualizar(Integer id, EstadoRequest request) {
        EstadosSistema estado = repository.findById(id).orElseThrow(()
            -> new RecursoNoEncontradoException(id + " id Estado no encontrado"));

        mapper.actualizarEstado(estado, request);

        estado = repository.save(estado);

        return mapper.toResponse(estado);

    }

    public List<EstadoResponse> estadosCodigo(String codigo){
        List<EstadosSistema> estados = repository.findByTipoCodigo(codigo);
        List<EstadoResponse> listaEstados = new ArrayList<>();

        for(EstadosSistema estado : estados){
            EstadoResponse agregar = mapper.toResponse(estado);
            listaEstados.add(agregar);
        }

        return listaEstados;
    }

    public void eliminar(Integer id) {
        repository.findById(id).orElseThrow(()
            -> new RecursoNoEncontradoException(id + " id Estado no encontrado"));

        repository.deleteById(id);
    }
}