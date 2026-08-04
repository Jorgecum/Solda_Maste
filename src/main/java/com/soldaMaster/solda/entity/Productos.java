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
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Size(max = 100)
    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_descripcion", length = 150, nullable = false)
    private String nombreDescripcion;

    @NotNull
    @Column(name = "precio_venta", nullable = false)
    private BigDecimal precioVenta;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "stock_minimo")
    private Integer stockMinimo;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "maneja_lote")
    private Boolean manejaLote;

    @Column(name = "precio_mayorista")
    private BigDecimal precioMayorista;

    @Column(name = "precio_distribuidor")
    private BigDecimal precioDistribuidor;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_unico", length = 10, nullable = false)
    private String codigoUnico;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<MovimientosInventario> movimientosInventarioList;

    @OneToMany(mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<DetalleVentas> detalleVentasList;

    @OneToMany(mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<DetalleOrden> detalleOrdenList;

    @OneToMany(mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<Lotes> lotesList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Categorias idCategoria;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado", nullable = false)
    private EstadosSistema idEstado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_medida", referencedColumnName = "id_medida")
    private Medidas idUnidadMedida;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<DetalleNotaCredito> detalleNotaCreditoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos", fetch = FetchType.LAZY)
    private List<ProductosRelacionados> productosRelacionadosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos1", fetch = FetchType.LAZY)
    private List<ProductosRelacionados> productosRelacionadosList1;

    @OneToMany(mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<DetalleCompras> detalleComprasList;

}