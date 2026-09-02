package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soldaMaster.solda.entity.OrdenesCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenesCompra, Integer>{
    List<OrdenesCompra> findByIdEstadoOrden_IdEstado(Integer idEstado);
    @Query("SELECT o FROM OrdenesCompra o " +
       "WHERE (:search IS NULL OR :search = '' " +
       "OR CAST(o.idOrden AS string) LIKE %:search% " +
       "OR LOWER(o.idProveedor.nombreRazonSocial) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<OrdenesCompra> buscarOrdenesPaginadas(@Param("search") String search, Pageable pageable);

}
