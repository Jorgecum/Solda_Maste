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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "ordenes_compra")
@NamedQueries({
    @NamedQuery(name = "OrdenesCompra.findAll", query = "SELECT o FROM OrdenesCompra o"),
    @NamedQuery(name = "OrdenesCompra.findByIdOrden", query = "SELECT o FROM OrdenesCompra o WHERE o.idOrden = :idOrden"),
    @NamedQuery(name = "OrdenesCompra.findByFechaPedido", query = "SELECT o FROM OrdenesCompra o WHERE o.fechaPedido = :fechaPedido"),
    @NamedQuery(name = "OrdenesCompra.findByFechaEntrega", query = "SELECT o FROM OrdenesCompra o WHERE o.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "OrdenesCompra.findByTotalEstimado", query = "SELECT o FROM OrdenesCompra o WHERE o.totalEstimado = :totalEstimado")})
public class OrdenesCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_orden")
    private Integer idOrden;
    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_estimado")
    private BigDecimal totalEstimado;
    @OneToMany(mappedBy = "idOrden", fetch = FetchType.LAZY)
    private List<Compras> comprasList;
    @OneToMany(mappedBy = "idOrden", fetch = FetchType.LAZY)
    private List<DetalleOrden> detalleOrdenList;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_entidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Entidades idProveedor;
    @JoinColumn(name = "id_estado_orden", referencedColumnName = "id_estado")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadosSistema idEstadoOrden;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios idUsuario;

    public OrdenesCompra() {
    }

    public OrdenesCompra(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public BigDecimal getTotalEstimado() {
        return totalEstimado;
    }

    public void setTotalEstimado(BigDecimal totalEstimado) {
        this.totalEstimado = totalEstimado;
    }

    public List<Compras> getComprasList() {
        return comprasList;
    }

    public void setComprasList(List<Compras> comprasList) {
        this.comprasList = comprasList;
    }

    public List<DetalleOrden> getDetalleOrdenList() {
        return detalleOrdenList;
    }

    public void setDetalleOrdenList(List<DetalleOrden> detalleOrdenList) {
        this.detalleOrdenList = detalleOrdenList;
    }

    public Entidades getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Entidades idProveedor) {
        this.idProveedor = idProveedor;
    }

    public EstadosSistema getIdEstadoOrden() {
        return idEstadoOrden;
    }

    public void setIdEstadoOrden(EstadosSistema idEstadoOrden) {
        this.idEstadoOrden = idEstadoOrden;
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
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenesCompra)) {
            return false;
        }
        OrdenesCompra other = (OrdenesCompra) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.OrdenesCompra[ idOrden=" + idOrden + " ]";
    }
    
}
