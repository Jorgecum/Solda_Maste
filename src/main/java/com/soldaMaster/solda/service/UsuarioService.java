package com.soldaMaster.solda.service;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.UsuarioCredenciales;
import com.soldaMaster.solda.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    public Boolean logear(UsuarioCredenciales credenciales){
        
        return repository.findByCredenciales(credenciales.getUsuario(),credenciales.getPassword());
    }
}
