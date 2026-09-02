package com.soldaMaster.solda.repository;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soldaMaster.solda.entity.Ventas;

public interface VentaRepository extends JpaRepository<Ventas, Integer> {
    Ventas findTopByTipoComprobanteOrderByIdVentaDesc(String comprobante); 
    List<Ventas> findTop20ByOrderByFechaEmisionDesc();

    @Query("SELECT COALESCE(SUM(v.total), 0) FROM Ventas v WHERE YEAR(v.fechaEmision) = YEAR(CURRENT_DATE) AND MONTH(v.fechaEmision) = MONTH(CURRENT_DATE) AND DAY(v.fechaEmision) = DAY(CURRENT_DATE) AND v.idEstadoVenta.idEstado != 7")
    BigDecimal sumarVentasHoy();    
    
    @Query("SELECT COALESCE(SUM(v.total), 0) FROM Ventas v WHERE YEAR(v.fechaEmision) = YEAR(CURRENT_DATE) AND MONTH(v.fechaEmision) = MONTH(CURRENT_DATE) AND v.idEstadoVenta.idEstado != 7")
    BigDecimal sumarVentasMes();
}
