package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;

import com.soldaMaster.solda.dto.CategoriaResponse;
import com.soldaMaster.solda.entity.Categorias;

@Mapper(componentModel = "spring")
public abstract class CategoriaMapper {
    
    public abstract CategoriaResponse toResponse(Categorias cat);
}
