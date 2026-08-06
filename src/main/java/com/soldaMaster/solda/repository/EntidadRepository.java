package com.soldaMaster.solda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.Entidades;

public interface EntidadRepository extends JpaRepository<Entidades, Integer> {

    boolean existsByNumeroDocumento(String numeroDocumento);

}
