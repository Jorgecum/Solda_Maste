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
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "detalle_nota_credito")
@NamedQueries({
    @NamedQuery(name = "DetalleNotaCredito.findAll", query = "SELECT d FROM DetalleNotaCredito d"),
    @NamedQuery(name = "DetalleNotaCredito.findByIdDetalleNota", query = "SELECT d FROM DetalleNotaCredito d WHERE d.idDetalleNota = :idDetalleNota"),
    @NamedQuery(name = "DetalleNotaCredito.findByCantidad", query = "SELECT d FROM DetalleNotaCredito d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleNotaCredito.findByPrecioUnitario", query = "SELECT d FROM DetalleNotaCredito d WHERE d.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "DetalleNotaCredito.findBySubtotal", query = "SELECT d FROM DetalleNotaCredito d WHERE d.subtotal = :subtotal")})
public class DetalleNotaCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_nota")
    private Integer idDetalleNota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    @JoinColumn(name = "id_lote", referencedColumnName = "id_lote")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lotes idLote;
    @JoinColumn(name = "id_nota", referencedColumnName = "id_nota")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NotasCredito idNota;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;

    public DetalleNotaCredito() {
    }

    public DetalleNotaCredito(Integer idDetalleNota) {
        this.idDetalleNota = idDetalleNota;
    }

    public DetalleNotaCredito(Integer idDetalleNota, int cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        this.idDetalleNota = idDetalleNota;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public Integer getIdDetalleNota() {
        return idDetalleNota;
    }

    public void setIdDetalleNota(Integer idDetalleNota) {
        this.idDetalleNota = idDetalleNota;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Lotes getIdLote() {
        return idLote;
    }

    public void setIdLote(Lotes idLote) {
        this.idLote = idLote;
    }

    public NotasCredito getIdNota() {
        return idNota;
    }

    public void setIdNota(NotasCredito idNota) {
        this.idNota = idNota;
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
        hash += (idDetalleNota != null ? idDetalleNota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleNotaCredito)) {
            return false;
        }
        DetalleNotaCredito other = (DetalleNotaCredito) object;
        if ((this.idDetalleNota == null && other.idDetalleNota != null) || (this.idDetalleNota != null && !this.idDetalleNota.equals(other.idDetalleNota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.DetalleNotaCredito[ idDetalleNota=" + idDetalleNota + " ]";
    }
    
}
