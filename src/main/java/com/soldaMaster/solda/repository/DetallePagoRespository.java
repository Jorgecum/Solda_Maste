package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.DetallePagos;

public interface DetallePagoRespository extends JpaRepository<DetallePagos, Integer> {
    List<DetallePagos> findByIdCuota_IdCuota(Integer idCuota);
}
