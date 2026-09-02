package com.soldaMaster.solda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soldaMaster.solda.dto.CompraRequest;
import com.soldaMaster.solda.dto.CompraResponse;
import com.soldaMaster.solda.entity.Compras;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.mapper.CompraMapper;
import com.soldaMaster.solda.repository.CompraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository repository;
    private final CompraMapper mapper;
    private final DetalleCompraService deCompraService;
    private final OrdenCompraService oCompraService;

    @Transactional
    public CompraResponse crearCompra(CompraRequest request){
        Compras ingresada = mapper.toEntity(request);
        ingresada = repository.save(ingresada);

        deCompraService.crearDetalle(request.getListaDetalleCompra(),ingresada.getIdCompra(), ingresada.getSerieCorrelativa());

        if(request.getIdOrden() != null){
            oCompraService.aprobarOrden(request.getIdOrden());
        }

        return mapper.toResponse(ingresada);
    }

    public Page<CompraResponse> listarComprasPaginadas(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idCompra").descending());
        
        Page<Compras> paginaCompras = repository.buscarComprasPaginadas(search, pageable);
        
        return paginaCompras.map(compra -> mapper.toResponse(compra));
    }

    public CompraResponse obtenerCompra(Integer idCompra){
        Compras encontrada = repository.findById(idCompra)
            .orElseThrow(()-> new RecursoNoEncontradoException(idCompra + " Compra no encontrada"));

        CompraResponse compraObtenida = mapper.toResponse(encontrada);

        compraObtenida.setListaDetalleCompra(deCompraService.obtenerDetalleCompra(idCompra));

        return compraObtenida;
    }
}
