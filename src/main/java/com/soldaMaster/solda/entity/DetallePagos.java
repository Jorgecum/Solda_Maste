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
@Table(name = "detalle_pagos")
@NamedQueries({
    @NamedQuery(name = "DetallePagos.findAll", query = "SELECT d FROM DetallePagos d"),
    @NamedQuery(name = "DetallePagos.findByIdDetallePago", query = "SELECT d FROM DetallePagos d WHERE d.idDetallePago = :idDetallePago"),
    @NamedQuery(name = "DetallePagos.findByMonto", query = "SELECT d FROM DetallePagos d WHERE d.monto = :monto")})
public class DetallePagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_pago")
    private Integer idDetallePago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private BigDecimal monto;
    @JoinColumn(name = "id_cuota", referencedColumnName = "id_cuota")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuotas idCuota;
    @JoinColumn(name = "id_pago", referencedColumnName = "id_pago")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pagos idPago;

    public DetallePagos() {
    }

    public DetallePagos(Integer idDetallePago) {
        this.idDetallePago = idDetallePago;
    }

    public DetallePagos(Integer idDetallePago, BigDecimal monto) {
        this.idDetallePago = idDetallePago;
        this.monto = monto;
    }

    public Integer getIdDetallePago() {
        return idDetallePago;
    }

    public void setIdDetallePago(Integer idDetallePago) {
        this.idDetallePago = idDetallePago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Cuotas getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(Cuotas idCuota) {
        this.idCuota = idCuota;
    }

    public Pagos getIdPago() {
        return idPago;
    }

    public void setIdPago(Pagos idPago) {
        this.idPago = idPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallePago != null ? idDetallePago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePagos)) {
            return false;
        }
        DetallePagos other = (DetallePagos) object;
        if ((this.idDetallePago == null && other.idDetallePago != null) || (this.idDetallePago != null && !this.idDetallePago.equals(other.idDetallePago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.DetallePagos[ idDetallePago=" + idDetallePago + " ]";
    }
    
}
