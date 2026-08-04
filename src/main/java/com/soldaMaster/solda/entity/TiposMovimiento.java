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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "tipos_movimiento")
@NamedQueries({
    @NamedQuery(name = "TiposMovimiento.findAll", query = "SELECT t FROM TiposMovimiento t"),
    @NamedQuery(name = "TiposMovimiento.findByIdTipoMovimiento", query = "SELECT t FROM TiposMovimiento t WHERE t.idTipoMovimiento = :idTipoMovimiento"),
    @NamedQuery(name = "TiposMovimiento.findByNombre", query = "SELECT t FROM TiposMovimiento t WHERE t.nombre = :nombre")})
public class TiposMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_movimiento")
    private Integer idTipoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMovimiento", fetch = FetchType.LAZY)
    private List<MovimientosInventario> movimientosInventarioList;

    public TiposMovimiento() {
    }

    public TiposMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public TiposMovimiento(Integer idTipoMovimiento, String nombre) {
        this.idTipoMovimiento = idTipoMovimiento;
        this.nombre = nombre;
    }

    public Integer getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<MovimientosInventario> getMovimientosInventarioList() {
        return movimientosInventarioList;
    }

    public void setMovimientosInventarioList(List<MovimientosInventario> movimientosInventarioList) {
        this.movimientosInventarioList = movimientosInventarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMovimiento != null ? idTipoMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposMovimiento)) {
            return false;
        }
        TiposMovimiento other = (TiposMovimiento) object;
        if ((this.idTipoMovimiento == null && other.idTipoMovimiento != null) || (this.idTipoMovimiento != null && !this.idTipoMovimiento.equals(other.idTipoMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.TiposMovimiento[ idTipoMovimiento=" + idTipoMovimiento + " ]";
    }
    
}
