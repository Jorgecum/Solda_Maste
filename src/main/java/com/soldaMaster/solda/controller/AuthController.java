package com.soldaMaster.solda.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.UsuarioCredenciales;
import com.soldaMaster.solda.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UsuarioService service;

    @PostMapping("/login")
    public String logear(@RequestBody UsuarioCredenciales request){
        return service.logear(request);
    }
}
