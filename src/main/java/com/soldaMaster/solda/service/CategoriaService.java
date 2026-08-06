package com.soldaMaster.solda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.CategoriaResponse;
import com.soldaMaster.solda.entity.Categorias;
import com.soldaMaster.solda.mapper.CategoriaMapper;
import com.soldaMaster.solda.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    public List<CategoriaResponse> mostrarCategorias(){
        List<Categorias> categorias = repository.findAll();
        
        List<CategoriaResponse> listaCategorias = new ArrayList<>();

        for(Categorias cat : categorias){
            CategoriaResponse agregar = mapper.toResponse(cat);
            listaCategorias.add(agregar);
        }

        return listaCategorias;
    }
}
