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
@Table(name = "compras")
@NamedQueries({
    @NamedQuery(name = "Compras.findAll", query = "SELECT c FROM Compras c"),
    @NamedQuery(name = "Compras.findByIdCompra", query = "SELECT c FROM Compras c WHERE c.idCompra = :idCompra"),
    @NamedQuery(name = "Compras.findByTipoComprobante", query = "SELECT c FROM Compras c WHERE c.tipoComprobante = :tipoComprobante"),
    @NamedQuery(name = "Compras.findBySerieCorrelativa", query = "SELECT c FROM Compras c WHERE c.serieCorrelativa = :serieCorrelativa"),
    @NamedQuery(name = "Compras.findByFechaCompra", query = "SELECT c FROM Compras c WHERE c.fechaCompra = :fechaCompra"),
    @NamedQuery(name = "Compras.findByMontoTotal", query = "SELECT c FROM Compras c WHERE c.montoTotal = :montoTotal")})
public class Compras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_compra")
    private Integer idCompra;
    @Size(max = 20)
    @Column(name = "tipo_comprobante")
    private String tipoComprobante;
    @Size(max = 50)
    @Column(name = "serie_correlativa")
    private String serieCorrelativa;
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_total")
    private BigDecimal montoTotal;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_entidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Entidades idProveedor;
    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrdenesCompra idOrden;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios idUsuario;
    @OneToMany(mappedBy = "idCompra", fetch = FetchType.LAZY)
    private List<DetalleCompras> detalleComprasList;

    public Compras() {
    }

    public Compras(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getSerieCorrelativa() {
        return serieCorrelativa;
    }

    public void setSerieCorrelativa(String serieCorrelativa) {
        this.serieCorrelativa = serieCorrelativa;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Entidades getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Entidades idProveedor) {
        this.idProveedor = idProveedor;
    }

    public OrdenesCompra getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(OrdenesCompra idOrden) {
        this.idOrden = idOrden;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compras)) {
            return false;
        }
        Compras other = (Compras) object;
        if ((this.idCompra == null && other.idCompra != null) || (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.Compras[ idCompra=" + idCompra + " ]";
    }
    
}
