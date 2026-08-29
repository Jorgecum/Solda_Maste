package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.MovimientosInventario;


public interface MovimientoInventarioRepository extends JpaRepository<MovimientosInventario, Integer>{
    List<MovimientosInventario> findByIdProducto_IdProducto(Integer idProducto);
    List<MovimientosInventario> findByIdLote_IdLote(Integer idLote);
    List<MovimientosInventario> findTop20ByOrderByFechaDesc();
    List<MovimientosInventario> findTop100ByOrderByFechaDesc();
}
