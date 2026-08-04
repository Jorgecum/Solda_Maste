/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soldaMaster.solda.entity;

import jakarta.persistence.Basic;
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
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "detalle_compras")
@NamedQueries({
    @NamedQuery(name = "DetalleCompras.findAll", query = "SELECT d FROM DetalleCompras d"),
    @NamedQuery(name = "DetalleCompras.findByIdDetalleCompra", query = "SELECT d FROM DetalleCompras d WHERE d.idDetalleCompra = :idDetalleCompra"),
    @NamedQuery(name = "DetalleCompras.findByCantidad", query = "SELECT d FROM DetalleCompras d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleCompras.findByPrecioCostoUnitario", query = "SELECT d FROM DetalleCompras d WHERE d.precioCostoUnitario = :precioCostoUnitario")})
public class DetalleCompras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_compra")
    private Integer idDetalleCompra;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_costo_unitario")
    private BigDecimal precioCostoUnitario;
    @JoinColumn(name = "id_compra", referencedColumnName = "id_compra")
    @ManyToOne(fetch = FetchType.LAZY)
    private Compras idCompra;
    @JoinColumn(name = "id_lote", referencedColumnName = "id_lote")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lotes idLote;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Productos idProducto;

    public DetalleCompras() {
    }

    public DetalleCompras(Integer idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public Integer getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Integer idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioCostoUnitario() {
        return precioCostoUnitario;
    }

    public void setPrecioCostoUnitario(BigDecimal precioCostoUnitario) {
        this.precioCostoUnitario = precioCostoUnitario;
    }

    public Compras getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Compras idCompra) {
        this.idCompra = idCompra;
    }

    public Lotes getIdLote() {
        return idLote;
    }

    public void setIdLote(Lotes idLote) {
        this.idLote = idLote;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleCompra != null ? idDetalleCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCompras)) {
            return false;
        }
        DetalleCompras other = (DetalleCompras) object;
        if ((this.idDetalleCompra == null && other.idDetalleCompra != null) || (this.idDetalleCompra != null && !this.idDetalleCompra.equals(other.idDetalleCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.DetalleCompras[ idDetalleCompra=" + idDetalleCompra + " ]";
    }
    
}
