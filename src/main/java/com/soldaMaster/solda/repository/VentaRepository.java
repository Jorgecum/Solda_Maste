package com.soldaMaster.solda.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.Ventas;

public interface VentaRepository extends JpaRepository<Ventas, Integer> {
    Ventas findTopByTipoComprobanteOrderByIdVentaDesc(String comprobante); 
    List<Ventas> findTop20ByOrderByFechaEmisionDesc();
}
