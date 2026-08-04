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
@Table(name = "medidas")
@NamedQueries({
    @NamedQuery(name = "Medidas.findAll", query = "SELECT m FROM Medidas m"),
    @NamedQuery(name = "Medidas.findByIdMedida", query = "SELECT m FROM Medidas m WHERE m.idMedida = :idMedida"),
    @NamedQuery(name = "Medidas.findByNombre", query = "SELECT m FROM Medidas m WHERE m.nombre = :nombre")})
public class Medidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_medida")
    private Integer idMedida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosSistema idEstado;
    @OneToMany(mappedBy = "idUnidadMedida", fetch = FetchType.LAZY)
    private List<Productos> productosList;

    public Medidas() {
    }

    public Medidas(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public Medidas(Integer idMedida, String nombre) {
        this.idMedida = idMedida;
        this.nombre = nombre;
    }

    public Integer getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadosSistema getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosSistema idEstado) {
        this.idEstado = idEstado;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedida != null ? idMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medidas)) {
            return false;
        }
        Medidas other = (Medidas) object;
        if ((this.idMedida == null && other.idMedida != null) || (this.idMedida != null && !this.idMedida.equals(other.idMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.Medidas[ idMedida=" + idMedida + " ]";
    }
    
}
