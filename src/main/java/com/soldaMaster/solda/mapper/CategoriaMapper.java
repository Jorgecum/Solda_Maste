package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.CategoriaResponse;
import com.soldaMaster.solda.entity.Categorias;
import com.soldaMaster.solda.entity.EstadosSistema;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.CategoriaRepository;

@Mapper(componentModel = "spring")
public abstract class CategoriaMapper {
    @Autowired
    protected CategoriaRepository repository;

    public abstract CategoriaResponse toResponse(Categorias cat);
    public Categorias mapId(Integer id){
        return repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException( id + " Categoria no encotrada"));
    }

    public Categorias mapNombre(String nombre){
        return repository.findByNombre(nombre)
            .orElseGet(()->{
                EstadosSistema estado = new EstadosSistema();
                estado.setIdEstado(1);
                Categorias nueva = new Categorias();
                nueva.setNombre(nombre);
                nueva.setIdEstado(estado);
                return repository.save(nueva);
            });
    }
}
