package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.OrdenesCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenesCompra, Integer>{
    List<OrdenesCompra> findByIdEstadoOrden_IdEstado(Integer idEstado);

}
