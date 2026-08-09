package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.EstadosSistema;

public interface EstadoRepository extends JpaRepository<EstadosSistema, Integer> {

    List<EstadosSistema> findByTipoCodigo(String codigo);

}