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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lotes")
@Getter
@Setter
@NoArgsConstructor
public class Lotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Integer idLote;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "numero_lote", nullable = false, length = 100)
    private String numeroLote;

    @Column(name = "fecha_entrada")
    private LocalDateTime fechaEntrada;

    @Column(name = "stock_lote")
    private Integer stockLote;

    @OneToMany(mappedBy = "idLote", fetch = FetchType.LAZY)
    private List<MovimientosInventario> movimientosInventarioList;

    @OneToMany(mappedBy = "idLote", fetch = FetchType.LAZY)
    private List<DetalleVentas> detalleVentasList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_certificado", referencedColumnName = "id_certificado")
    private Certificados idCertificado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Productos idProducto;

    @OneToMany(mappedBy = "idLote", fetch = FetchType.LAZY)
    private List<DetalleNotaCredito> detalleNotaCreditoList;

    @OneToMany(mappedBy = "idLote", fetch = FetchType.LAZY)
    private List<DetalleCompras> detalleComprasList;

}