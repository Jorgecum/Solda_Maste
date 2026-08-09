package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.MovimientosInventario;


public interface MovimientoInventarioRepository extends JpaRepository<MovimientosInventario, Integer>{
    List<MovimientosInventario> findByIdProducto(Integer idProducto);
    List<MovimientosInventario> findByIdLote(Integer idLote);
    
}
