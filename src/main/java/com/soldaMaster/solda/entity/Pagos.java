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
@Table(name = "pagos")
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p"),
    @NamedQuery(name = "Pagos.findByIdPago", query = "SELECT p FROM Pagos p WHERE p.idPago = :idPago"),
    @NamedQuery(name = "Pagos.findByMontoTotal", query = "SELECT p FROM Pagos p WHERE p.montoTotal = :montoTotal"),
    @NamedQuery(name = "Pagos.findByFecha", query = "SELECT p FROM Pagos p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Pagos.findByReferenciaPago", query = "SELECT p FROM Pagos p WHERE p.referenciaPago = :referenciaPago")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pago")
    private Integer idPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_total")
    private BigDecimal montoTotal;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 100)
    @Column(name = "referencia_pago")
    private String referenciaPago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPago", fetch = FetchType.LAZY)
    private List<DetallePagos> detallePagosList;
    @JoinColumn(name = "id_metodo_pago", referencedColumnName = "id_metodo_pago")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MetodosPago idMetodoPago;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ventas idVenta;

    public Pagos() {
    }

    public Pagos(Integer idPago) {
        this.idPago = idPago;
    }

    public Pagos(Integer idPago, BigDecimal montoTotal) {
        this.idPago = idPago;
        this.montoTotal = montoTotal;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public List<DetallePagos> getDetallePagosList() {
        return detallePagosList;
    }

    public void setDetallePagosList(List<DetallePagos> detallePagosList) {
        this.detallePagosList = detallePagosList;
    }

    public MetodosPago getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(MetodosPago idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
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
        hash += (idPago != null ? idPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.idPago == null && other.idPago != null) || (this.idPago != null && !this.idPago.equals(other.idPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.Pagos[ idPago=" + idPago + " ]";
    }
    
}
