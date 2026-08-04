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
@Table(name = "ventas")
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v"),
    @NamedQuery(name = "Ventas.findByIdVenta", query = "SELECT v FROM Ventas v WHERE v.idVenta = :idVenta"),
    @NamedQuery(name = "Ventas.findByIdVentaOrigen", query = "SELECT v FROM Ventas v WHERE v.idVentaOrigen = :idVentaOrigen"),
    @NamedQuery(name = "Ventas.findBySerieCorrelativa", query = "SELECT v FROM Ventas v WHERE v.serieCorrelativa = :serieCorrelativa"),
    @NamedQuery(name = "Ventas.findByTipoComprobante", query = "SELECT v FROM Ventas v WHERE v.tipoComprobante = :tipoComprobante"),
    @NamedQuery(name = "Ventas.findByFechaEmision", query = "SELECT v FROM Ventas v WHERE v.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "Ventas.findByTotal", query = "SELECT v FROM Ventas v WHERE v.total = :total"),
    @NamedQuery(name = "Ventas.findBySubtotal", query = "SELECT v FROM Ventas v WHERE v.subtotal = :subtotal"),
    @NamedQuery(name = "Ventas.findByDescuentoGlobal", query = "SELECT v FROM Ventas v WHERE v.descuentoGlobal = :descuentoGlobal")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_venta")
    private Integer idVenta;
    @Column(name = "id_venta_origen")
    private Integer idVentaOrigen;
    @Size(max = 50)
    @Column(name = "serie_correlativa")
    private String serieCorrelativa;
    @Size(max = 20)
    @Column(name = "tipo_comprobante")
    private String tipoComprobante;
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_global")
    private BigDecimal descuentoGlobal;
    @OneToMany(mappedBy = "idVenta", fetch = FetchType.LAZY)
    private List<DetalleVentas> detalleVentasList;
    @OneToMany(mappedBy = "idVenta", fetch = FetchType.LAZY)
    private List<Cuotas> cuotasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta", fetch = FetchType.LAZY)
    private List<Pagos> pagosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta", fetch = FetchType.LAZY)
    private List<NotasCredito> notasCreditoList;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_entidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Entidades idCliente;
    @JoinColumn(name = "id_estado_venta", referencedColumnName = "id_estado")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadosSistema idEstadoVenta;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios idUsuario;

    public Ventas() {
    }

    public Ventas(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Ventas(Integer idVenta, BigDecimal subtotal, BigDecimal descuentoGlobal) {
        this.idVenta = idVenta;
        this.subtotal = subtotal;
        this.descuentoGlobal = descuentoGlobal;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdVentaOrigen() {
        return idVentaOrigen;
    }

    public void setIdVentaOrigen(Integer idVentaOrigen) {
        this.idVentaOrigen = idVentaOrigen;
    }

    public String getSerieCorrelativa() {
        return serieCorrelativa;
    }

    public void setSerieCorrelativa(String serieCorrelativa) {
        this.serieCorrelativa = serieCorrelativa;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDescuentoGlobal() {
        return descuentoGlobal;
    }

    public void setDescuentoGlobal(BigDecimal descuentoGlobal) {
        this.descuentoGlobal = descuentoGlobal;
    }

    public List<DetalleVentas> getDetalleVentasList() {
        return detalleVentasList;
    }

    public void setDetalleVentasList(List<DetalleVentas> detalleVentasList) {
        this.detalleVentasList = detalleVentasList;
    }

    public List<Cuotas> getCuotasList() {
        return cuotasList;
    }

    public void setCuotasList(List<Cuotas> cuotasList) {
        this.cuotasList = cuotasList;
    }

    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    public List<NotasCredito> getNotasCreditoList() {
        return notasCreditoList;
    }

    public void setNotasCreditoList(List<NotasCredito> notasCreditoList) {
        this.notasCreditoList = notasCreditoList;
    }

    public Entidades getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Entidades idCliente) {
        this.idCliente = idCliente;
    }

    public EstadosSistema getIdEstadoVenta() {
        return idEstadoVenta;
    }

    public void setIdEstadoVenta(EstadosSistema idEstadoVenta) {
        this.idEstadoVenta = idEstadoVenta;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.Ventas[ idVenta=" + idVenta + " ]";
    }
    
}
