package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.RolResponse;
import com.soldaMaster.solda.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;
    @GetMapping("/roles")    
    public List<RolResponse> mostrarRoles(){
        return service.mostrarRoles();
    }
}
