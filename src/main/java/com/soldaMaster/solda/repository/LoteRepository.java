package com.soldaMaster.solda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.Lotes;

public interface LoteRepository extends JpaRepository<Lotes, Integer>{
    List<Lotes> findByIdProducto_IdProducto(Integer idProducto);
}
