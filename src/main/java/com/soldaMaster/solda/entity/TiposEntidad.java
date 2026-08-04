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
@Table(name = "tipos_entidad")
@NamedQueries({
    @NamedQuery(name = "TiposEntidad.findAll", query = "SELECT t FROM TiposEntidad t"),
    @NamedQuery(name = "TiposEntidad.findByIdTipoEntidad", query = "SELECT t FROM TiposEntidad t WHERE t.idTipoEntidad = :idTipoEntidad"),
    @NamedQuery(name = "TiposEntidad.findByNombre", query = "SELECT t FROM TiposEntidad t WHERE t.nombre = :nombre")})
public class TiposEntidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_entidad")
    private Integer idTipoEntidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idTipoEntidad", fetch = FetchType.LAZY)
    private List<Entidades> entidadesList;

    public TiposEntidad() {
    }

    public TiposEntidad(Integer idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    public TiposEntidad(Integer idTipoEntidad, String nombre) {
        this.idTipoEntidad = idTipoEntidad;
        this.nombre = nombre;
    }

    public Integer getIdTipoEntidad() {
        return idTipoEntidad;
    }

    public void setIdTipoEntidad(Integer idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Entidades> getEntidadesList() {
        return entidadesList;
    }

    public void setEntidadesList(List<Entidades> entidadesList) {
        this.entidadesList = entidadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEntidad != null ? idTipoEntidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposEntidad)) {
            return false;
        }
        TiposEntidad other = (TiposEntidad) object;
        if ((this.idTipoEntidad == null && other.idTipoEntidad != null) || (this.idTipoEntidad != null && !this.idTipoEntidad.equals(other.idTipoEntidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.TiposEntidad[ idTipoEntidad=" + idTipoEntidad + " ]";
    }
    
}
