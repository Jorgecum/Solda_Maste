package com.soldaMaster.solda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.MedidaResponse;
import com.soldaMaster.solda.entity.EstadosSistema;
import com.soldaMaster.solda.entity.Medidas;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.MedidaRepository;

@Mapper(componentModel = "spring")
public abstract class MedidaMapper {
    @Autowired
    protected MedidaRepository repository;
    public abstract MedidaResponse toResponse(Medidas medida);

    public abstract List<MedidaResponse> toResponseList(List<Medidas> medidas);

    public Medidas mapId(Integer id){
        return repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException(id+ " Medida no encotrada"));
    }

    public Medidas mapNombre(String nombre){
        return repository.findByNombre(nombre)
            .orElseGet(()->{
                EstadosSistema estado = new EstadosSistema();
                estado.setIdEstado(1);
                Medidas nueva = new Medidas();
                nueva.setNombre(nombre);
                nueva.setIdEstado(estado);
                return repository.save(nueva);
            });
    }
}
