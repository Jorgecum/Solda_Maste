package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.TipoEntidadResponse;
import com.soldaMaster.solda.service.TipoEntidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tipo-entidad")
@RequiredArgsConstructor
public class TipoEntidadController {
    private final TipoEntidadService service;

    @GetMapping
    public List<TipoEntidadResponse> mostrarTipoEntidades(){
        return service.listar();
    }

}
