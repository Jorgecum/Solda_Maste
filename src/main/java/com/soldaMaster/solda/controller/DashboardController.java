package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.ActividadDTO;
import com.soldaMaster.solda.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dService;

    @GetMapping("/actividad")
    public List<ActividadDTO> obtenerActividad(){
        return dService.obtenerActividadReciente();
    }
}
