package com.soldaMaster.solda.entity;

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
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer idCompra;

    @Size(max = 20)
    @Column(name = "tipo_comprobante", length = 20)
    private String tipoComprobante;

    @Size(max = 50)
    @Column(name = "serie_correlativa", length = 50)
    private String serieCorrelativa;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    @Column(name = "monto_total")
    private BigDecimal montoTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_entidad")
    private Entidades idProveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden")
    private OrdenesCompra idOrden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuarios idUsuario;

    @OneToMany(mappedBy = "idCompra", fetch = FetchType.LAZY)
    private List<DetalleCompras> detalleComprasList;

}