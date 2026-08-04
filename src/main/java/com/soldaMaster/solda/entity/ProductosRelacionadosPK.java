/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soldaMaster.solda.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author augusto
 */
@Embeddable
public class ProductosRelacionadosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto")
    private int idProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto_relacionado")
    private int idProductoRelacionado;

    public ProductosRelacionadosPK() {
    }

    public ProductosRelacionadosPK(int idProducto, int idProductoRelacionado) {
        this.idProducto = idProducto;
        this.idProductoRelacionado = idProductoRelacionado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdProductoRelacionado() {
        return idProductoRelacionado;
    }

    public void setIdProductoRelacionado(int idProductoRelacionado) {
        this.idProductoRelacionado = idProductoRelacionado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProducto;
        hash += (int) idProductoRelacionado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosRelacionadosPK)) {
            return false;
        }
        ProductosRelacionadosPK other = (ProductosRelacionadosPK) object;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idProductoRelacionado != other.idProductoRelacionado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.ProductosRelacionadosPK[ idProducto=" + idProducto + ", idProductoRelacionado=" + idProductoRelacionado + " ]";
    }
    
}
