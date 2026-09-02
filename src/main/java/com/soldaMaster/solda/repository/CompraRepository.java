package com.soldaMaster.solda.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soldaMaster.solda.entity.Compras;

public interface CompraRepository extends JpaRepository<Compras, Integer> {
    @Query("SELECT c FROM Compras c " +
       "WHERE (:search IS NULL OR :search = '' " +
       "OR LOWER(c.serieCorrelativa) LIKE LOWER(CONCAT('%', :search, '%')) " +
       "OR LOWER(c.idProveedor.nombreRazonSocial) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Compras> buscarComprasPaginadas(@Param("search") String search, Pageable pageable);
    
    @Query("SELECT COALESCE(SUM(c.montoTotal), 0) FROM Compras c WHERE YEAR(c.fechaCompra) = YEAR(CURRENT_DATE) AND MONTH(c.fechaCompra) = MONTH(CURRENT_DATE)")
    BigDecimal sumarComprasMes();
}
