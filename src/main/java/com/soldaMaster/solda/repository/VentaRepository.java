package com.soldaMaster.solda.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.Ventas;

public interface VentaRepository extends JpaRepository<Ventas, Integer> {
    Ventas findTopByTipoComprobanteOrderByIdVentaDesc(String comprobante); 
}
