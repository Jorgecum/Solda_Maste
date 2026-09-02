package com.soldaMaster.solda.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soldaMaster.solda.dto.DetalleOrdenResponse;
import com.soldaMaster.solda.dto.OrdenCompraRequest;
import com.soldaMaster.solda.dto.OrdenCompraResponse;
import com.soldaMaster.solda.entity.EstadosSistema;
import com.soldaMaster.solda.entity.OrdenesCompra;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.mapper.OrdenCompraMapper;
import com.soldaMaster.solda.repository.OrdenCompraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdenCompraService {
    private final OrdenCompraMapper mapper;
    private final OrdenCompraRepository repository;
    private final DetalleOrdenService dOrdenService;
    private final EstadoService eService;

    @Transactional
    public OrdenCompraResponse crearOrden(OrdenCompraRequest request){
        OrdenesCompra ordenIngresada = mapper.toEntity(request);

        ordenIngresada = repository.save(ordenIngresada);

        dOrdenService.crearDetalle(request.getListaDetalles(), ordenIngresada.getIdOrden());

        return mapper.toResponse(ordenIngresada);

    }

    public Page<OrdenCompraResponse> listarOrdenesPag(int page, int size, String busqueda){
        Pageable pageable = PageRequest.of(page, size, Sort.by("idOrden").descending());
        Page<OrdenesCompra> paginas = repository.buscarOrdenesPaginadas(busqueda, pageable);

        return paginas.map(orden -> mapper.toResponse(orden));
    }

    public OrdenCompraResponse obtenerOrdenCompra(Integer idOrden){
        OrdenesCompra orden = repository.findById(idOrden)
            .orElseThrow(()-> new RecursoNoEncontradoException(idOrden + " Orden no encotrada"));

        OrdenCompraResponse ordenEncotrada = mapper.toResponse(orden);

        List<DetalleOrdenResponse> listaDetalles = dOrdenService.detalleOrden(ordenEncotrada.getIdOrden());

        ordenEncotrada.setListaDetalles(listaDetalles);

        return ordenEncotrada;
    }

    public OrdenCompraResponse rechazarOrdenCompra(Integer idOrden){
        OrdenesCompra orden = repository.findById(idOrden)
            .orElseThrow(()-> new RecursoNoEncontradoException(idOrden + " Orden no encotrada"));
        
        
        EstadosSistema estadoRechazado = eService.obtenerPorIdSistema(5);
        orden.setIdEstadoOrden(estadoRechazado);
        orden = repository.save(orden);

        return mapper.toResponse(orden);
    }

    public List<OrdenCompraResponse> ordenesPendientes(){
        List<OrdenesCompra> ordenesPendientes = repository.findByIdEstadoOrden_IdEstado(3);
        return mapper.toResponseList(ordenesPendientes);
    }

    public void aprobarOrden(Integer idOrden){
        OrdenesCompra orden = repository.findById(idOrden)
            .orElseThrow(()-> new RecursoNoEncontradoException(idOrden + " Orden no encotrada"));
        
        
        EstadosSistema estadoAprobado = eService.obtenerPorIdSistema(23);
        orden.setIdEstadoOrden(estadoAprobado);
        orden = repository.save(orden);

    }
}
