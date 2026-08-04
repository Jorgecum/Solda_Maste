/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soldaMaster.solda.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "productos")
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdProducto", query = "SELECT p FROM Productos p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Productos.findByCodigoBarras", query = "SELECT p FROM Productos p WHERE p.codigoBarras = :codigoBarras"),
    @NamedQuery(name = "Productos.findByNombreDescripcion", query = "SELECT p FROM Productos p WHERE p.nombreDescripcion = :nombreDescripcion"),
    @NamedQuery(name = "Productos.findByPrecioVenta", query = "SELECT p FROM Productos p WHERE p.precioVenta = :precioVenta"),
    @NamedQuery(name = "Productos.findByStock", query = "SELECT p FROM Productos p WHERE p.stock = :stock"),
    @NamedQuery(name = "Productos.findByStockMinimo", query = "SELECT p FROM Productos p WHERE p.stockMinimo = :stockMinimo"),
    @NamedQuery(name = "Productos.findByImagenUrl", query = "SELECT p FROM Productos p WHERE p.imagenUrl = :imagenUrl"),
    @NamedQuery(name = "Productos.findByManejaLote", query = "SELECT p FROM Productos p WHERE p.manejaLote = :manejaLote"),
    @NamedQuery(name = "Productos.findByPrecioMayorista", query = "SELECT p FROM Productos p WHERE p.precioMayorista = :precioMayorista"),
    @NamedQuery(name = "Productos.findByPrecioDistribuidor", query = "SELECT p FROM Productos p WHERE p.precioDistribuidor = :precioDistribuidor"),
    @NamedQuery(name = "Productos.findByCodigoUnico", query = "SELECT p FROM Productos p WHERE p.codigoUnico = :codigoUnico")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto")
    private Integer idProducto;
    @Size(max = 100)
    @Column(name = "codigo_barras")
    private String codigoBarras;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_descripcion")
    private String nombreDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_venta")
    private BigDecimal precioVenta;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "stock_minimo")
    private Integer stockMinimo;
    @Size(max = 2147483647)
    @Column(name = "imagen_url")
    private String imagenUrl;
    @Column(name = "maneja_lote")
    private Boolean manejaLote;
    @Column(name = "precio_mayorista")
    private BigDecimal precioMayorista;
    @Column(name = "precio_distribuidor")
    private BigDecimal precioDistribuidor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_unico")
    private String codigoUnico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<MovimientosInventario> movimientosInventarioList;
    @OneToMany(mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<DetalleVentas> detalleVentasList;
    @OneToMany(mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<DetalleOrden> detalleOrdenList;
    @OneToMany(mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<Lotes> lotesList;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categorias idCategoria;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosSistema idEstado;
    @JoinColumn(name = "id_unidad_medida", referencedColumnName = "id_medida")
    @ManyToOne(fetch = FetchType.LAZY)
    private Medidas idUnidadMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<DetalleNotaCredito> detalleNotaCreditoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos", fetch = FetchType.LAZY)
    private List<ProductosRelacionados> productosRelacionadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos1", fetch = FetchType.LAZY)
    private List<ProductosRelacionados> productosRelacionadosList1;
    @OneToMany(mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<DetalleCompras> detalleComprasList;

    public Productos() {
    }

    public Productos(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Productos(Integer idProducto, String nombreDescripcion, BigDecimal precioVenta, String codigoUnico) {
        this.idProducto = idProducto;
        this.nombreDescripcion = nombreDescripcion;
        this.precioVenta = precioVenta;
        this.codigoUnico = codigoUnico;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombreDescripcion() {
        return nombreDescripcion;
    }

    public void setNombreDescripcion(String nombreDescripcion) {
        this.nombreDescripcion = nombreDescripcion;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Boolean getManejaLote() {
        return manejaLote;
    }

    public void setManejaLote(Boolean manejaLote) {
        this.manejaLote = manejaLote;
    }

    public BigDecimal getPrecioMayorista() {
        return precioMayorista;
    }

    public void setPrecioMayorista(BigDecimal precioMayorista) {
        this.precioMayorista = precioMayorista;
    }

    public BigDecimal getPrecioDistribuidor() {
        return precioDistribuidor;
    }

    public void setPrecioDistribuidor(BigDecimal precioDistribuidor) {
        this.precioDistribuidor = precioDistribuidor;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public List<MovimientosInventario> getMovimientosInventarioList() {
        return movimientosInventarioList;
    }

    public void setMovimientosInventarioList(List<MovimientosInventario> movimientosInventarioList) {
        this.movimientosInventarioList = movimientosInventarioList;
    }

    public List<DetalleVentas> getDetalleVentasList() {
        return detalleVentasList;
    }

    public void setDetalleVentasList(List<DetalleVentas> detalleVentasList) {
        this.detalleVentasList = detalleVentasList;
    }

    public List<DetalleOrden> getDetalleOrdenList() {
        return detalleOrdenList;
    }

    public void setDetalleOrdenList(List<DetalleOrden> detalleOrdenList) {
        this.detalleOrdenList = detalleOrdenList;
    }

    public List<Lotes> getLotesList() {
        return lotesList;
    }

    public void setLotesList(List<Lotes> lotesList) {
        this.lotesList = lotesList;
    }

    public Categorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    public EstadosSistema getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosSistema idEstado) {
        this.idEstado = idEstado;
    }

    public Medidas getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(Medidas idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public List<DetalleNotaCredito> getDetalleNotaCreditoList() {
        return detalleNotaCreditoList;
    }

    public void setDetalleNotaCreditoList(List<DetalleNotaCredito> detalleNotaCreditoList) {
        this.detalleNotaCreditoList = detalleNotaCreditoList;
    }

    public List<ProductosRelacionados> getProductosRelacionadosList() {
        return productosRelacionadosList;
    }

    public void setProductosRelacionadosList(List<ProductosRelacionados> productosRelacionadosList) {
        this.productosRelacionadosList = productosRelacionadosList;
    }

    public List<ProductosRelacionados> getProductosRelacionadosList1() {
        return productosRelacionadosList1;
    }

    public void setProductosRelacionadosList1(List<ProductosRelacionados> productosRelacionadosList1) {
        this.productosRelacionadosList1 = productosRelacionadosList1;
    }

    public List<DetalleCompras> getDetalleComprasList() {
        return detalleComprasList;
    }

    public void setDetalleComprasList(List<DetalleCompras> detalleComprasList) {
        this.detalleComprasList = detalleComprasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.Productos[ idProducto=" + idProducto + " ]";
    }
    
}
