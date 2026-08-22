package com.soldaMaster.solda.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@NoArgsConstructor
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "id_venta_origen")
    private Integer idVentaOrigen;

    @Size(max = 50)
    @Column(name = "serie_correlativa", length = 50)
    private String serieCorrelativa;

    @Size(max = 20)
    @Column(name = "tipo_comprobante", length = 20)
    private String tipoComprobante;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision = LocalDateTime.now();

    @Column(name = "total")
    private BigDecimal total;

    @NotNull
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @NotNull
    @Column(name = "descuento_global", nullable = false)
    private BigDecimal descuentoGlobal;

    @OneToMany(mappedBy = "idVenta", fetch = FetchType.LAZY)
    private List<DetalleVentas> detalleVentasList;

    @OneToMany(mappedBy = "idVenta", fetch = FetchType.LAZY)
    private List<Cuotas> cuotasList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta", fetch = FetchType.LAZY)
    private List<Pagos> pagosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta", fetch = FetchType.LAZY)
    private List<NotasCredito> notasCreditoList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_entidad")
    private Entidades idCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_venta", referencedColumnName = "id_estado")
    private EstadosSistema idEstadoVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuarios idUsuario;

}