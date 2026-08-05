package com.soldaMaster.solda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {

    Boolean findByCredenciales (String username, String password);

}   
