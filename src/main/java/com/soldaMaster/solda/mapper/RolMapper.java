package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.RolResponse;
import com.soldaMaster.solda.entity.Roles;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.RolRepository;

@Mapper(componentModel = "spring")
public abstract class RolMapper {
     @Autowired 
     protected RolRepository repository;
     public abstract  RolResponse toResponse(Roles rol);

     public Roles map(Integer id){
          return repository.findById(id)
               .orElseThrow(()-> new RecursoNoEncontradoException(id + " Rol no encontrado")); 
     }
     
}
