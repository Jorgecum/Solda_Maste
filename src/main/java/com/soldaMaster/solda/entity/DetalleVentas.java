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
@Table(name = "detalle_ventas")
@NamedQueries({
    @NamedQuery(name = "DetalleVentas.findAll", query = "SELECT d FROM DetalleVentas d"),
    @NamedQuery(name = "DetalleVentas.findByIdDetalle", query = "SELECT d FROM DetalleVentas d WHERE d.idDetalle = :idDetalle"),
    @NamedQuery(name = "DetalleVentas.findByCantidad", query = "SELECT d FROM DetalleVentas d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleVentas.findByPrecioUnitario", query = "SELECT d FROM DetalleVentas d WHERE d.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "DetalleVentas.findByDescuentoProducto", query = "SELECT d FROM DetalleVentas d WHERE d.descuentoProducto = :descuentoProducto"),
    @NamedQuery(name = "DetalleVentas.findBySubTotal", query = "SELECT d FROM DetalleVentas d WHERE d.subTotal = :subTotal")})
public class DetalleVentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle")
    private Integer idDetalle;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "descuento_producto")
    private BigDecimal descuentoProducto;
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    @JoinColumn(name = "id_lote", referencedColumnName = "id_lote")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lotes idLote;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Productos idProducto;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ventas idVenta;

    public DetalleVentas() {
    }

    public DetalleVentas(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getDescuentoProducto() {
        return descuentoProducto;
    }

    public void setDescuentoProducto(BigDecimal descuentoProducto) {
        this.descuentoProducto = descuentoProducto;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
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

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleVentas)) {
            return false;
        }
        DetalleVentas other = (DetalleVentas) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.DetalleVentas[ idDetalle=" + idDetalle + " ]";
    }
    
}
