package com.soldaMaster.solda.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.EntidadRequest;
import com.soldaMaster.solda.dto.EntidadResponse;
import com.soldaMaster.solda.entity.Entidades;
import com.soldaMaster.solda.mapper.EntidadMapper;
import com.soldaMaster.solda.mapper.EstadoMapper;
import com.soldaMaster.solda.mapper.TipoEntidadMapper;
import com.soldaMaster.solda.repository.EntidadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntidadService {

    private final EntidadRepository repository;
    private final EntidadMapper mapper;
    private final EstadoMapper estadoMapper;
    private final TipoEntidadMapper tipoEntidadMapper;

    public List<EntidadResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    
    public Page<EntidadResponse> listarPaginado(int page, int size, String tipoEntidad, String tipoDocumento, String busqueda) {
        if (page < 0) page = 0;

        String tEntidad = (tipoEntidad != null && !tipoEntidad.equalsIgnoreCase("Todos") && !tipoEntidad.isEmpty()) ? tipoEntidad : null;
        String tDoc = (tipoDocumento != null && !tipoDocumento.equalsIgnoreCase("Todos") && !tipoDocumento.isEmpty()) ? tipoDocumento : null;
        String busq = (busqueda != null && !busqueda.trim().isEmpty()) ? busqueda.trim() : null;

        Page<Entidades> pagina = repository.buscarEntidadesPaginadas(tEntidad, tDoc, busq, PageRequest.of(page, size));
        return pagina.map(mapper::toResponse);
    }

    public EntidadResponse obtenerPorId(Integer id) {
        Entidades entidad = mapper.map(id);
        return mapper.toResponse(entidad);
    }

    public EntidadResponse crear(EntidadRequest request) {
        Entidades entidad = mapper.toEntity(request);
        entidad = repository.save(entidad);
        return mapper.toResponse(entidad);
    }

    public EntidadResponse actualizar(Integer id, EntidadRequest request) {
        Entidades entidad = mapper.map(id);

        entidad.setTipoDocumento(request.getTipoDocumento());
        entidad.setNumeroDocumento(request.getNumeroDocumento());
        entidad.setNombreRazonSocial(request.getNombreRazonSocial());
        entidad.setDireccion(request.getDireccion());
        entidad.setTelefono(request.getTelefono());
        entidad.setEmail(request.getEmail());
        entidad.setIdEstado(estadoMapper.map(request.getIdEstado()));

        if (request.getIdTipoEntidad() != null) {
            entidad.setIdTipoEntidad(tipoEntidadMapper.map(request.getIdTipoEntidad()));
        } else {
            entidad.setIdTipoEntidad(null);
        }

        entidad = repository.save(entidad);
        return mapper.toResponse(entidad);
    }

    public void eliminar(Integer id) {
        Entidades entidad = mapper.map(id);
        repository.delete(entidad);
    }
}