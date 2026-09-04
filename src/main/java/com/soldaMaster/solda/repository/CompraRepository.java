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

   @Query("SELECT c FROM Compras c WHERE " +
           "(CAST(:idProveedor AS integer) IS NULL OR c.idProveedor.idEntidad = :idProveedor) AND " +
           "(CAST(:fechaInicio AS timestamp) IS NULL OR c.fechaCompra >= :fechaInicio) AND " +
           "(CAST(:fechaFin AS timestamp) IS NULL OR c.fechaCompra <= :fechaFin) " +
           "ORDER BY c.fechaCompra DESC")
    Page<Compras> buscarParaReporte(@Param("idProveedor") Integer idProveedor, 
                                    @Param("fechaInicio") java.time.LocalDateTime fechaInicio, 
                                    @Param("fechaFin") java.time.LocalDateTime fechaFin, 
                                    Pageable pageable);

    @Query("SELECT COALESCE(SUM(c.montoTotal), 0) FROM Compras c WHERE " +
           "(CAST(:idProveedor AS integer) IS NULL OR c.idProveedor.idEntidad = :idProveedor) AND " +
           "(CAST(:fechaInicio AS timestamp) IS NULL OR c.fechaCompra >= :fechaInicio) AND " +
           "(CAST(:fechaFin AS timestamp) IS NULL OR c.fechaCompra <= :fechaFin)")
    BigDecimal sumarTotalReporte(@Param("idProveedor") Integer idProveedor, 
                                 @Param("fechaInicio") java.time.LocalDateTime fechaInicio, 
                                 @Param("fechaFin") java.time.LocalDateTime fechaFin);

       Page<Compras> findByIdProveedor_IdEntidadOrderByFechaCompraDesc(Integer idProveedor, Pageable pageable);
}
