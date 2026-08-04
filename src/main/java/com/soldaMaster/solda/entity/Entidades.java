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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "entidades")
@NamedQueries({
    @NamedQuery(name = "Entidades.findAll", query = "SELECT e FROM Entidades e"),
    @NamedQuery(name = "Entidades.findByIdEntidad", query = "SELECT e FROM Entidades e WHERE e.idEntidad = :idEntidad"),
    @NamedQuery(name = "Entidades.findByTipoDocumento", query = "SELECT e FROM Entidades e WHERE e.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Entidades.findByNumeroDocumento", query = "SELECT e FROM Entidades e WHERE e.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Entidades.findByNombreRazonSocial", query = "SELECT e FROM Entidades e WHERE e.nombreRazonSocial = :nombreRazonSocial"),
    @NamedQuery(name = "Entidades.findByDireccion", query = "SELECT e FROM Entidades e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Entidades.findByTelefono", query = "SELECT e FROM Entidades e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Entidades.findByEmail", query = "SELECT e FROM Entidades e WHERE e.email = :email")})
public class Entidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_entidad")
    private Integer idEntidad;
    @Size(max = 20)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Size(max = 20)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_razon_social")
    private String nombreRazonSocial;
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "idEntidad", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;
    @OneToMany(mappedBy = "idProveedor", fetch = FetchType.LAZY)
    private List<Compras> comprasList;
    @OneToMany(mappedBy = "idProveedor", fetch = FetchType.LAZY)
    private List<OrdenesCompra> ordenesCompraList;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosSistema idEstado;
    @JoinColumn(name = "id_tipo_entidad", referencedColumnName = "id_tipo_entidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private TiposEntidad idTipoEntidad;
    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<Ventas> ventasList;

    public Entidades() {
    }

    public Entidades(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Entidades(Integer idEntidad, String nombreRazonSocial) {
        this.idEntidad = idEntidad;
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public List<Compras> getComprasList() {
        return comprasList;
    }

    public void setComprasList(List<Compras> comprasList) {
        this.comprasList = comprasList;
    }

    public List<OrdenesCompra> getOrdenesCompraList() {
        return ordenesCompraList;
    }

    public void setOrdenesCompraList(List<OrdenesCompra> ordenesCompraList) {
        this.ordenesCompraList = ordenesCompraList;
    }

    public EstadosSistema getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosSistema idEstado) {
        this.idEstado = idEstado;
    }

    public TiposEntidad getIdTipoEntidad() {
        return idTipoEntidad;
    }

    public void setIdTipoEntidad(TiposEntidad idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
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
        hash += (idEntidad != null ? idEntidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidades)) {
            return false;
        }
        Entidades other = (Entidades) object;
        if ((this.idEntidad == null && other.idEntidad != null) || (this.idEntidad != null && !this.idEntidad.equals(other.idEntidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.Entidades[ idEntidad=" + idEntidad + " ]";
    }
    
}
