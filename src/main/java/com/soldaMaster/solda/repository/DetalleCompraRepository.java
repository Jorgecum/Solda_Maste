package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.DetalleCompras;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompras, Integer>{
    List<DetalleCompras> findByIdCompra_IdCompra(Integer idCompra);
}
