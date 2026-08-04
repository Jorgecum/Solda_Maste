/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soldaMaster.solda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "productos_relacionados")
@NamedQueries({
    @NamedQuery(name = "ProductosRelacionados.findAll", query = "SELECT p FROM ProductosRelacionados p"),
    @NamedQuery(name = "ProductosRelacionados.findByIdProducto", query = "SELECT p FROM ProductosRelacionados p WHERE p.productosRelacionadosPK.idProducto = :idProducto"),
    @NamedQuery(name = "ProductosRelacionados.findByIdProductoRelacionado", query = "SELECT p FROM ProductosRelacionados p WHERE p.productosRelacionadosPK.idProductoRelacionado = :idProductoRelacionado"),
    @NamedQuery(name = "ProductosRelacionados.findByFrecuencia", query = "SELECT p FROM ProductosRelacionados p WHERE p.frecuencia = :frecuencia")})
public class ProductosRelacionados implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductosRelacionadosPK productosRelacionadosPK;
    @Column(name = "frecuencia")
    private Integer frecuencia;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos productos;
    @JoinColumn(name = "id_producto_relacionado", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos productos1;

    public ProductosRelacionados() {
    }

    public ProductosRelacionados(ProductosRelacionadosPK productosRelacionadosPK) {
        this.productosRelacionadosPK = productosRelacionadosPK;
    }

    public ProductosRelacionados(int idProducto, int idProductoRelacionado) {
        this.productosRelacionadosPK = new ProductosRelacionadosPK(idProducto, idProductoRelacionado);
    }

    public ProductosRelacionadosPK getProductosRelacionadosPK() {
        return productosRelacionadosPK;
    }

    public void setProductosRelacionadosPK(ProductosRelacionadosPK productosRelacionadosPK) {
        this.productosRelacionadosPK = productosRelacionadosPK;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Productos getProductos1() {
        return productos1;
    }

    public void setProductos1(Productos productos1) {
        this.productos1 = productos1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productosRelacionadosPK != null ? productosRelacionadosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosRelacionados)) {
            return false;
        }
        ProductosRelacionados other = (ProductosRelacionados) object;
        if ((this.productosRelacionadosPK == null && other.productosRelacionadosPK != null) || (this.productosRelacionadosPK != null && !this.productosRelacionadosPK.equals(other.productosRelacionadosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.ProductosRelacionados[ productosRelacionadosPK=" + productosRelacionadosPK + " ]";
    }
    
}
