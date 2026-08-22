package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.UsuarioRequest;
import com.soldaMaster.solda.dto.UsuarioResponse;
import com.soldaMaster.solda.entity.Usuarios;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.UsuarioRepository;

@Mapper(componentModel = "spring", uses = {EntidadMapper.class, EstadoMapper.class, RolMapper.class})
public abstract class UsuarioMapper {
    @Autowired
    protected UsuarioRepository repository;
    
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "comprasList", ignore = true)
    @Mapping(target = "ordenesCompraList", ignore = true)
    @Mapping(target = "ventasList", ignore = true)
    public abstract Usuarios toEntity(UsuarioRequest request );

    public abstract UsuarioResponse toResponse(Usuarios usuarios);

    public Usuarios map(Integer id){
        return repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException(id + " Usuario no encotrado"));
    }
}
