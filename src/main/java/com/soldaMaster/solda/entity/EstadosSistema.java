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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "estados_sistema")
@NamedQueries({
    @NamedQuery(name = "EstadosSistema.findAll", query = "SELECT e FROM EstadosSistema e"),
    @NamedQuery(name = "EstadosSistema.findByIdEstado", query = "SELECT e FROM EstadosSistema e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "EstadosSistema.findByTipoCodigo", query = "SELECT e FROM EstadosSistema e WHERE e.tipoCodigo = :tipoCodigo"),
    @NamedQuery(name = "EstadosSistema.findByNombre", query = "SELECT e FROM EstadosSistema e WHERE e.nombre = :nombre")})
public class EstadosSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado")
    private Integer idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipo_codigo")
    private String tipoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Medidas> medidasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;
    @OneToMany(mappedBy = "idEstadoCuota", fetch = FetchType.LAZY)
    private List<Cuotas> cuotasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Productos> productosList;
    @OneToMany(mappedBy = "idEstadoOrden", fetch = FetchType.LAZY)
    private List<OrdenesCompra> ordenesCompraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Categorias> categoriasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Entidades> entidadesList;
    @OneToMany(mappedBy = "idEstadoVenta", fetch = FetchType.LAZY)
    private List<Ventas> ventasList;

    public EstadosSistema() {
    }

    public EstadosSistema(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public EstadosSistema(Integer idEstado, String tipoCodigo, String nombre) {
        this.idEstado = idEstado;
        this.tipoCodigo = tipoCodigo;
        this.nombre = nombre;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getTipoCodigo() {
        return tipoCodigo;
    }

    public void setTipoCodigo(String tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Medidas> getMedidasList() {
        return medidasList;
    }

    public void setMedidasList(List<Medidas> medidasList) {
        this.medidasList = medidasList;
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public List<Cuotas> getCuotasList() {
        return cuotasList;
    }

    public void setCuotasList(List<Cuotas> cuotasList) {
        this.cuotasList = cuotasList;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    public List<OrdenesCompra> getOrdenesCompraList() {
        return ordenesCompraList;
    }

    public void setOrdenesCompraList(List<OrdenesCompra> ordenesCompraList) {
        this.ordenesCompraList = ordenesCompraList;
    }

    public List<Categorias> getCategoriasList() {
        return categoriasList;
    }

    public void setCategoriasList(List<Categorias> categoriasList) {
        this.categoriasList = categoriasList;
    }

    public List<Entidades> getEntidadesList() {
        return entidadesList;
    }

    public void setEntidadesList(List<Entidades> entidadesList) {
        this.entidadesList = entidadesList;
    }

    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadosSistema)) {
            return false;
        }
        EstadosSistema other = (EstadosSistema) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.EstadosSistema[ idEstado=" + idEstado + " ]";
    }
    
}
