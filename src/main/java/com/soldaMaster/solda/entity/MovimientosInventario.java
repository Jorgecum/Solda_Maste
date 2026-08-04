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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "movimientos_inventario")
@NamedQueries({
    @NamedQuery(name = "MovimientosInventario.findAll", query = "SELECT m FROM MovimientosInventario m"),
    @NamedQuery(name = "MovimientosInventario.findByIdMovimiento", query = "SELECT m FROM MovimientosInventario m WHERE m.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "MovimientosInventario.findByCantidad", query = "SELECT m FROM MovimientosInventario m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MovimientosInventario.findByFecha", query = "SELECT m FROM MovimientosInventario m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "MovimientosInventario.findByReferencia", query = "SELECT m FROM MovimientosInventario m WHERE m.referencia = :referencia")})
public class MovimientosInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 100)
    @Column(name = "referencia")
    private String referencia;
    @JoinColumn(name = "id_lote", referencedColumnName = "id_lote")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lotes idLote;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;
    @JoinColumn(name = "id_tipo_movimiento", referencedColumnName = "id_tipo_movimiento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TiposMovimiento idTipoMovimiento;

    public MovimientosInventario() {
    }

    public MovimientosInventario(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public MovimientosInventario(Integer idMovimiento, int cantidad) {
        this.idMovimiento = idMovimiento;
        this.cantidad = cantidad;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    public TiposMovimiento getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(TiposMovimiento idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento != null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientosInventario)) {
            return false;
        }
        MovimientosInventario other = (MovimientosInventario) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.MovimientosInventario[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
