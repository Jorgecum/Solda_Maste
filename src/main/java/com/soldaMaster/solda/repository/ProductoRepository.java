package com.soldaMaster.solda.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soldaMaster.solda.entity.Productos;

public interface ProductoRepository extends JpaRepository<Productos, Integer>{
    @Query("SELECT COALESCE(SUM(p.stock), 0) FROM Productos p")
    Long sumarStockTotal();

    @Query("SELECT COALESCE(SUM(p.stock * p.precioVenta), 0) FROM Productos p")
    BigDecimal sumarValorInventario();

    @Query("SELECT COUNT(p) FROM Productos p WHERE p.stock <= p.stockMinimo")
    Long contarStockCritico();

    @Query("SELECT p FROM Productos p " +
           "WHERE (:idCategoria IS NULL OR p.idCategoria.idCategoria = :idCategoria) " +
           "AND (:busqueda IS NULL OR :busqueda = '' " +
           "OR LOWER(p.nombreDescripcion) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
           "OR LOWER(p.codigoBarras) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
           "OR LOWER(p.codigoUnico) LIKE LOWER(CONCAT('%', :busqueda, '%')))")
    Page<Productos> listarPaginado(
            @Param("busqueda") String busqueda,
            @Param("idCategoria") Integer idCategoria,
            Pageable pageable
    );
}
