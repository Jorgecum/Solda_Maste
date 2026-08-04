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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "cuotas")
@NamedQueries({
    @NamedQuery(name = "Cuotas.findAll", query = "SELECT c FROM Cuotas c"),
    @NamedQuery(name = "Cuotas.findByIdCuota", query = "SELECT c FROM Cuotas c WHERE c.idCuota = :idCuota"),
    @NamedQuery(name = "Cuotas.findByNumeroCuota", query = "SELECT c FROM Cuotas c WHERE c.numeroCuota = :numeroCuota"),
    @NamedQuery(name = "Cuotas.findByFechaVencimiento", query = "SELECT c FROM Cuotas c WHERE c.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "Cuotas.findByMonto", query = "SELECT c FROM Cuotas c WHERE c.monto = :monto")})
public class Cuotas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuota")
    private Integer idCuota;
    @Column(name = "numero_cuota")
    private Integer numeroCuota;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private BigDecimal monto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuota", fetch = FetchType.LAZY)
    private List<DetallePagos> detallePagosList;
    @JoinColumn(name = "id_estado_cuota", referencedColumnName = "id_estado")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadosSistema idEstadoCuota;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ventas idVenta;

    public Cuotas() {
    }

    public Cuotas(Integer idCuota) {
        this.idCuota = idCuota;
    }

    public Integer getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(Integer idCuota) {
        this.idCuota = idCuota;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public List<DetallePagos> getDetallePagosList() {
        return detallePagosList;
    }

    public void setDetallePagosList(List<DetallePagos> detallePagosList) {
        this.detallePagosList = detallePagosList;
    }

    public EstadosSistema getIdEstadoCuota() {
        return idEstadoCuota;
    }

    public void setIdEstadoCuota(EstadosSistema idEstadoCuota) {
        this.idEstadoCuota = idEstadoCuota;
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
        hash += (idCuota != null ? idCuota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuotas)) {
            return false;
        }
        Cuotas other = (Cuotas) object;
        if ((this.idCuota == null && other.idCuota != null) || (this.idCuota != null && !this.idCuota.equals(other.idCuota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.Cuotas[ idCuota=" + idCuota + " ]";
    }
    
}
