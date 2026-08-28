package com.soldaMaster.solda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldaMaster.solda.dto.CuotaResponse;
import com.soldaMaster.solda.dto.PagoRequest;
import com.soldaMaster.solda.dto.PagoResponse;
import com.soldaMaster.solda.service.CuotaService;
import com.soldaMaster.solda.service.PagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pago")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService service;
    private final CuotaService cuotaService;

    @PostMapping()
    public PagoResponse abonoCuota(@RequestBody PagoRequest request){
        return service.distribuirPago(request);
    }

    @GetMapping("/cuotas/{idVenta}")
    public List<CuotaResponse> listaCuotasVenta(@PathVariable Integer idVenta){
        return cuotaService.cuotasDVenta(idVenta);
    }

    @GetMapping("/cuotas")
    public List<CuotaResponse> listaCuotasVenta(){
        return cuotaService.mostrarCuotas();
    }
    

    @GetMapping()
    public List<PagoResponse> mostrarPagos(){
        return service.mostrarPagos();
    }
    
}
