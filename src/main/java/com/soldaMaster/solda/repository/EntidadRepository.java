package com.soldaMaster.solda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soldaMaster.solda.entity.Entidades;

public interface EntidadRepository extends JpaRepository<Entidades, Integer> {

    boolean existsByNumeroDocumento(String numeroDocumento);
    @Query("SELECT COUNT(e) FROM Entidades e WHERE UPPER(e.idTipoEntidad.nombre) = 'CLIENTE'")
    Long contarClientes();
}
