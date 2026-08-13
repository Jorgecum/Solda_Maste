package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.LoteResponse;
import com.soldaMaster.solda.service.LoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lote")
@RequiredArgsConstructor
public class LoteController {
    private final LoteService service;

    @GetMapping("/producto/{id}")
    public List<LoteResponse> loteKardex(@PathVariable Integer id){
        return service.mostrarLotesProd(id);
    }
    
}
