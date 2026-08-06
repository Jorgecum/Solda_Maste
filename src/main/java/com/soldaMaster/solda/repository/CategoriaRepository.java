package com.soldaMaster.solda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.Categorias;

public interface CategoriaRepository extends JpaRepository<Categorias, Integer>{
    
}
