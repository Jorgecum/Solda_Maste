package com.soldaMaster.solda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.Medidas;

public interface MedidaRepository extends JpaRepository<Medidas, Integer>{
    Optional<Medidas> findByNombre(String nombre);
}
