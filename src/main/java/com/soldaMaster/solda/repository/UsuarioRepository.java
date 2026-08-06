package com.soldaMaster.solda.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldaMaster.solda.entity.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    
    Optional<Usuarios> findByUsuario(String usuario);
}   
