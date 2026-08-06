package com.soldaMaster.solda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.RolResponse;
import com.soldaMaster.solda.dto.UsuarioCredenciales;
import com.soldaMaster.solda.entity.Roles;
import com.soldaMaster.solda.entity.Usuarios;
import com.soldaMaster.solda.mapper.RolMapper;
import com.soldaMaster.solda.repository.RolRepository;
import com.soldaMaster.solda.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final RolRepository rolRepository;
    private final RolMapper rolMapper;


    public String logear(UsuarioCredenciales credenciales){
        String mensaje;

        Usuarios encontrado = repository.findByUsuario(credenciales.getUsuario())
            .orElseThrow(()-> new RuntimeException("usuario no encontrado"));

        Boolean coincide = passwordEncoder.matches(credenciales.getPassword(),encontrado.getPassword());

        if(coincide){
            mensaje = "Bienvenido";
        }else{
            mensaje = "constraseña incorrecta";
        }
            
        return mensaje;
        
    }

    public List<RolResponse> mostrarRoles(){
        List<Roles> roles = rolRepository.findAll();

        List<RolResponse> listaRoles = new ArrayList<>();

        for(Roles rol : roles){
            RolResponse agregar = rolMapper.toResponse(rol);
            listaRoles.add(agregar);
        }

        return listaRoles;
    }
}
