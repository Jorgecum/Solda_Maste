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
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "detalle_orden")
@NamedQueries({
    @NamedQuery(name = "DetalleOrden.findAll", query = "SELECT d FROM DetalleOrden d"),
    @NamedQuery(name = "DetalleOrden.findByIdDetalleOrden", query = "SELECT d FROM DetalleOrden d WHERE d.idDetalleOrden = :idDetalleOrden"),
    @NamedQuery(name = "DetalleOrden.findByCantidadPedida", query = "SELECT d FROM DetalleOrden d WHERE d.cantidadPedida = :cantidadPedida"),
    @NamedQuery(name = "DetalleOrden.findByPrecioUnitarioPactado", query = "SELECT d FROM DetalleOrden d WHERE d.precioUnitarioPactado = :precioUnitarioPactado")})
public class DetalleOrden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_orden")
    private Integer idDetalleOrden;
    @Column(name = "cantidad_pedida")
    private Integer cantidadPedida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario_pactado")
    private BigDecimal precioUnitarioPactado;
    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrdenesCompra idOrden;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Productos idProducto;

    public DetalleOrden() {
    }

    public DetalleOrden(Integer idDetalleOrden) {
        this.idDetalleOrden = idDetalleOrden;
    }

    public Integer getIdDetalleOrden() {
        return idDetalleOrden;
    }

    public void setIdDetalleOrden(Integer idDetalleOrden) {
        this.idDetalleOrden = idDetalleOrden;
    }

    public Integer getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(Integer cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public BigDecimal getPrecioUnitarioPactado() {
        return precioUnitarioPactado;
    }

    public void setPrecioUnitarioPactado(BigDecimal precioUnitarioPactado) {
        this.precioUnitarioPactado = precioUnitarioPactado;
    }

    public OrdenesCompra getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(OrdenesCompra idOrden) {
        this.idOrden = idOrden;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleOrden != null ? idDetalleOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleOrden)) {
            return false;
        }
        DetalleOrden other = (DetalleOrden) object;
        if ((this.idDetalleOrden == null && other.idDetalleOrden != null) || (this.idDetalleOrden != null && !this.idDetalleOrden.equals(other.idDetalleOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.DetalleOrden[ idDetalleOrden=" + idDetalleOrden + " ]";
    }
    
}
