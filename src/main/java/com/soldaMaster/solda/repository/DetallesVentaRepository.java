package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.DetalleVentas;

public interface DetallesVentaRepository extends JpaRepository<DetalleVentas, Integer>{
    List<DetalleVentas> findByIdVenta_IdVenta(Integer idVenta);
}
