package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.soldaMaster.solda.dto.MovimientoInventarioResponse;
import com.soldaMaster.solda.service.MovimientoInventarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kardex")
@RequiredArgsConstructor
public class MovimientoController {
    private final MovimientoInventarioService service;

    @GetMapping("/producto/{id}")
    public List<MovimientoInventarioResponse> productokardex(@PathVariable Integer id){
        return service.productoKardex(id);
    }

    

}
