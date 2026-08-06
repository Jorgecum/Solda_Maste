package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.CategoriaResponse;
import com.soldaMaster.solda.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService service;

    @GetMapping
    public List<CategoriaResponse> mostrarCategoria(){
        return service.mostrarCategorias();
    }
}
