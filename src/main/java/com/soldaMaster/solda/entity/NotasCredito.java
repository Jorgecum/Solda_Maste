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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "notas_credito")
@NamedQueries({
    @NamedQuery(name = "NotasCredito.findAll", query = "SELECT n FROM NotasCredito n"),
    @NamedQuery(name = "NotasCredito.findByIdNota", query = "SELECT n FROM NotasCredito n WHERE n.idNota = :idNota"),
    @NamedQuery(name = "NotasCredito.findBySerieCorrelativa", query = "SELECT n FROM NotasCredito n WHERE n.serieCorrelativa = :serieCorrelativa"),
    @NamedQuery(name = "NotasCredito.findByFechaEmision", query = "SELECT n FROM NotasCredito n WHERE n.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "NotasCredito.findByMotivo", query = "SELECT n FROM NotasCredito n WHERE n.motivo = :motivo"),
    @NamedQuery(name = "NotasCredito.findByMontoTotal", query = "SELECT n FROM NotasCredito n WHERE n.montoTotal = :montoTotal")})
public class NotasCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nota")
    private Integer idNota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "serie_correlativa")
    private String serieCorrelativa;
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Size(max = 2147483647)
    @Column(name = "motivo")
    private String motivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_total")
    private BigDecimal montoTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNota", fetch = FetchType.LAZY)
    private List<DetalleNotaCredito> detalleNotaCreditoList;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ventas idVenta;

    public NotasCredito() {
    }

    public NotasCredito(Integer idNota) {
        this.idNota = idNota;
    }

    public NotasCredito(Integer idNota, String serieCorrelativa, BigDecimal montoTotal) {
        this.idNota = idNota;
        this.serieCorrelativa = serieCorrelativa;
        this.montoTotal = montoTotal;
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public String getSerieCorrelativa() {
        return serieCorrelativa;
    }

    public void setSerieCorrelativa(String serieCorrelativa) {
        this.serieCorrelativa = serieCorrelativa;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<DetalleNotaCredito> getDetalleNotaCreditoList() {
        return detalleNotaCreditoList;
    }

    public void setDetalleNotaCreditoList(List<DetalleNotaCredito> detalleNotaCreditoList) {
        this.detalleNotaCreditoList = detalleNotaCreditoList;
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
        hash += (idNota != null ? idNota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotasCredito)) {
            return false;
        }
        NotasCredito other = (NotasCredito) object;
        if ((this.idNota == null && other.idNota != null) || (this.idNota != null && !this.idNota.equals(other.idNota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.NotasCredito[ idNota=" + idNota + " ]";
    }
    
}
