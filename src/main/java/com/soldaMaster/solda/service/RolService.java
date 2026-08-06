package com.soldaMaster.solda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.RolResponse;
import com.soldaMaster.solda.entity.Roles;
import com.soldaMaster.solda.mapper.RolMapper;
import com.soldaMaster.solda.repository.RolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolService {
    private final RolRepository repository;
    private final RolMapper mapper;

    public List<RolResponse> mostrarRoles(){
        List<Roles> roles = repository.findAll();

        List<RolResponse> listaRoles = new ArrayList<>();

        for(Roles rol : roles){
            RolResponse agregar = mapper.toResponse(rol);
            listaRoles.add(agregar);
        }

        return listaRoles;
    }

}
