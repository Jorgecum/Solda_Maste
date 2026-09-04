    package com.soldaMaster.solda.repository;


    import java.math.BigDecimal;
    import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soldaMaster.solda.entity.Ventas;

    public interface VentaRepository extends JpaRepository<Ventas, Integer> {
        Ventas findTopByTipoComprobanteOrderByIdVentaDesc(String comprobante); 
        List<Ventas> findTop20ByOrderByFechaEmisionDesc();

        @Query("SELECT COALESCE(SUM(v.total), 0) FROM Ventas v WHERE YEAR(v.fechaEmision) = YEAR(CURRENT_DATE) AND MONTH(v.fechaEmision) = MONTH(CURRENT_DATE) AND DAY(v.fechaEmision) = DAY(CURRENT_DATE) AND v.idEstadoVenta.idEstado != 7")
        BigDecimal sumarVentasHoy();    
        
        @Query("SELECT COALESCE(SUM(v.total), 0) FROM Ventas v WHERE YEAR(v.fechaEmision) = YEAR(CURRENT_DATE) AND MONTH(v.fechaEmision) = MONTH(CURRENT_DATE) AND v.idEstadoVenta.idEstado != 7")
        BigDecimal sumarVentasMes();

        @Query("SELECT COALESCE(SUM(v.total), 0) FROM Ventas v WHERE v.idEstadoVenta.idEstado != 7")
        BigDecimal sumarTotalGlobal();

        @Query("SELECT v FROM Ventas v WHERE " +
           "(CAST(:fechaInicio AS timestamp) IS NULL OR v.fechaEmision >= :fechaInicio) AND " +
           "(CAST(:fechaFin AS timestamp) IS NULL OR v.fechaEmision <= :fechaFin) AND " +
           "(CAST(:tipoComprobante AS text) IS NULL OR v.tipoComprobante = :tipoComprobante) AND " +
           "(CAST(:clienteBusqueda AS text) IS NULL OR " +
           "LOWER(v.idCliente.nombreRazonSocial) LIKE LOWER(CONCAT('%', CAST(:clienteBusqueda AS text), '%')) OR " +
           "v.idCliente.numeroDocumento LIKE CONCAT('%', CAST(:clienteBusqueda AS text), '%')) " +
           "ORDER BY v.fechaEmision DESC")
        Page<Ventas> buscarParaReporte(@Param("fechaInicio") java.time.LocalDateTime fechaInicio, 
                                    @Param("fechaFin") java.time.LocalDateTime fechaFin,
                                    @Param("tipoComprobante") String tipoComprobante,
                                    @Param("clienteBusqueda") String clienteBusqueda,
                                    Pageable pageable);
                                    
        @Query("SELECT COALESCE(SUM(v.total), 0) FROM Ventas v WHERE v.idEstadoVenta.idEstado != 7 AND " +
           "(CAST(:fechaInicio AS timestamp) IS NULL OR v.fechaEmision >= :fechaInicio) AND " +
           "(CAST(:fechaFin AS timestamp) IS NULL OR v.fechaEmision <= :fechaFin) AND " +
           "(CAST(:tipoComprobante AS text) IS NULL OR v.tipoComprobante = :tipoComprobante) AND " +
           "(CAST(:clienteBusqueda AS text) IS NULL OR " +
           "LOWER(v.idCliente.nombreRazonSocial) LIKE LOWER(CONCAT('%', CAST(:clienteBusqueda AS text), '%')) OR " +
           "v.idCliente.numeroDocumento LIKE CONCAT('%', CAST(:clienteBusqueda AS text), '%'))")
        BigDecimal sumarTotalReporte(@Param("fechaInicio") java.time.LocalDateTime fechaInicio, 
                                    @Param("fechaFin") java.time.LocalDateTime fechaFin,
                                    @Param("tipoComprobante") String tipoComprobante,
                                    @Param("clienteBusqueda") String clienteBusqueda);
        
        Page<Ventas> findByIdCliente_IdEntidadOrderByFechaEmisionDesc(Integer idCliente, Pageable pageable);
    }
