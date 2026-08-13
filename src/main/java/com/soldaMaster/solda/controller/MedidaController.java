package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.MedidaResponse;
import com.soldaMaster.solda.service.MedidaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medida")
@RequiredArgsConstructor
public class MedidaController {
    private final MedidaService service;

    @GetMapping
    public List<MedidaResponse> mostrarMedidas(){
        return service.mostrarMedidas();
    }
}
