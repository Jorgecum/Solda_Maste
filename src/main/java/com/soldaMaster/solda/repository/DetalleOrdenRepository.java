package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.DetalleOrden;

public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer>{
    List<DetalleOrden> findByIdOrden_IdOrden(Integer idOrden);
}
