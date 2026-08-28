package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soldaMaster.solda.entity.Cuotas;

public interface CuotaRepository extends JpaRepository<Cuotas, Integer>{
    List<Cuotas> findByIdVenta_IdVentaOrderByNumeroCuotaAsc(Integer idVenta);
    
    @Query("SELECT c FROM Cuotas c WHERE c.idEstadoCuota.idEstado IN (9, 11)")
    List<Cuotas> findCuotasPorCobrar();
}
