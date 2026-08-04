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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "lotes")
@NamedQueries({
    @NamedQuery(name = "Lotes.findAll", query = "SELECT l FROM Lotes l"),
    @NamedQuery(name = "Lotes.findByIdLote", query = "SELECT l FROM Lotes l WHERE l.idLote = :idLote"),
    @NamedQuery(name = "Lotes.findByNumeroLote", query = "SELECT l FROM Lotes l WHERE l.numeroLote = :numeroLote"),
    @NamedQuery(name = "Lotes.findByFechaEntrada", query = "SELECT l FROM Lotes l WHERE l.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "Lotes.findByStockLote", query = "SELECT l FROM Lotes l WHERE l.stockLote = :stockLote")})
public class Lotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lote")
    private Integer idLote;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "numero_lote")
    private String numeroLote;
    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrada;
    @Column(name = "stock_lote")
    private Integer stockLote;
    @OneToMany(mappedBy = "idLote", fetch = FetchType.LAZY)
    private List<MovimientosInventario> movimientosInventarioList;
    @OneToMany(mappedBy = "idLote", fetch = FetchType.LAZY)
    private List<DetalleVentas> detalleVentasList;
    @JoinColumn(name = "id_certificado", referencedColumnName = "id_certificado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Certificados idCertificado;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Productos idProducto;
    @OneToMany(mappedBy = "idLote", fetch = FetchType.LAZY)
    private List<DetalleNotaCredito> detalleNotaCreditoList;
    @OneToMany(mappedBy = "idLote", fetch = FetchType.LAZY)
    private List<DetalleCompras> detalleComprasList;

    public Lotes() {
    }

    public Lotes(Integer idLote) {
        this.idLote = idLote;
    }

    public Lotes(Integer idLote, String numeroLote) {
        this.idLote = idLote;
        this.numeroLote = numeroLote;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Integer getStockLote() {
        return stockLote;
    }

    public void setStockLote(Integer stockLote) {
        this.stockLote = stockLote;
    }

    public List<MovimientosInventario> getMovimientosInventarioList() {
        return movimientosInventarioList;
    }

    public void setMovimientosInventarioList(List<MovimientosInventario> movimientosInventarioList) {
        this.movimientosInventarioList = movimientosInventarioList;
    }

    public List<DetalleVentas> getDetalleVentasList() {
        return detalleVentasList;
    }

    public void setDetalleVentasList(List<DetalleVentas> detalleVentasList) {
        this.detalleVentasList = detalleVentasList;
    }

    public Certificados getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(Certificados idCertificado) {
        this.idCertificado = idCertificado;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public List<DetalleNotaCredito> getDetalleNotaCreditoList() {
        return detalleNotaCreditoList;
    }

    public void setDetalleNotaCreditoList(List<DetalleNotaCredito> detalleNotaCreditoList) {
        this.detalleNotaCreditoList = detalleNotaCreditoList;
    }

    public List<DetalleCompras> getDetalleComprasList() {
        return detalleComprasList;
    }

    public void setDetalleComprasList(List<DetalleCompras> detalleComprasList) {
        this.detalleComprasList = detalleComprasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLote != null ? idLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lotes)) {
            return false;
        }
        Lotes other = (Lotes) object;
        if ((this.idLote == null && other.idLote != null) || (this.idLote != null && !this.idLote.equals(other.idLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.Lotes[ idLote=" + idLote + " ]";
    }
    
}
