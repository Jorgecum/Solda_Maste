package com.soldaMaster.solda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soldaMaster.solda.entity.Entidades;

public interface EntidadRepository extends JpaRepository<Entidades, Integer> {

    boolean existsByNumeroDocumento(String numeroDocumento);
    @Query("SELECT COUNT(e) FROM Entidades e WHERE UPPER(e.idTipoEntidad.nombre) = 'CLIENTE'")
    Long contarClientes();

    @Query("SELECT e FROM Entidades e WHERE " +
           "(CAST(:tipoEntidad AS text) IS NULL OR UPPER(e.idTipoEntidad.nombre) = UPPER(CAST(:tipoEntidad AS text))) AND " +
           "(CAST(:tipoDocumento AS text) IS NULL OR e.tipoDocumento = :tipoDocumento) AND " +
           "(CAST(:busqueda AS text) IS NULL OR " +
           "LOWER(e.nombreRazonSocial) LIKE LOWER(CONCAT('%', CAST(:busqueda AS text), '%')) OR " +
           "e.numeroDocumento LIKE CONCAT('%', CAST(:busqueda AS text), '%')) " +
           "ORDER BY e.idEntidad DESC")
    Page<Entidades> buscarEntidadesPaginadas(
            @Param("tipoEntidad") String tipoEntidad, 
            @Param("tipoDocumento") String tipoDocumento, 
            @Param("busqueda") String busqueda, 
            Pageable pageable);
}
