package com.soldaMaster.solda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.Productos;

public interface ProductoRepository extends JpaRepository<Productos, Integer>{

}
