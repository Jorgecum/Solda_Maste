package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.EntidadRequest;
import com.soldaMaster.solda.dto.EntidadResponse;
import com.soldaMaster.solda.service.EntidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/entidad")
@RequiredArgsConstructor
public class EntidadController {
    private final EntidadService service;

    @GetMapping
    public List<EntidadResponse> mostrarEntidades(){
        return service.listar();
    }

    @PostMapping
    public EntidadResponse crearEntidad(@RequestBody EntidadRequest request){
        return service.crear(request);
    }

    @GetMapping("/{id}")
    public EntidadResponse obtenerEntidad(@PathVariable Integer id){
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public EntidadResponse actualizarEntidad(@PathVariable Integer id, @RequestBody EntidadRequest request){
        return service.actualizar(id, request);
    }

}
