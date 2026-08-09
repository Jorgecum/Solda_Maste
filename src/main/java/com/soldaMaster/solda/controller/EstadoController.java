package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.EstadoResponse;
import com.soldaMaster.solda.service.EstadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estado")
@RequiredArgsConstructor
public class EstadoController {
    private final EstadoService service;

    @GetMapping()
    public List<EstadoResponse> mostrarEstados(){
        return service.listar();
    }

    @GetMapping("/{codigo}")
    public List<EstadoResponse> estadosCodigo(@PathVariable String codigo){
        return service.estadosCodigo(codigo);
    }
}
