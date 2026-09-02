package com.soldaMaster.solda.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soldaMaster.solda.entity.Productos;

public interface ProductoRepository extends JpaRepository<Productos, Integer>{
    @Query("SELECT COALESCE(SUM(p.stock), 0) FROM Productos p")
    Long sumarStockTotal();

    @Query("SELECT COALESCE(SUM(p.stock * p.precioVenta), 0) FROM Productos p")
    BigDecimal sumarValorInventario();

    @Query("SELECT COUNT(p) FROM Productos p WHERE p.stock <= p.stockMinimo")
    Long contarStockCritico();
}
