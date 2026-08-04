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
@Table(name = "metodos_pago")
@NamedQueries({
    @NamedQuery(name = "MetodosPago.findAll", query = "SELECT m FROM MetodosPago m"),
    @NamedQuery(name = "MetodosPago.findByIdMetodoPago", query = "SELECT m FROM MetodosPago m WHERE m.idMetodoPago = :idMetodoPago"),
    @NamedQuery(name = "MetodosPago.findByNombre", query = "SELECT m FROM MetodosPago m WHERE m.nombre = :nombre")})
public class MetodosPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_metodo_pago")
    private Integer idMetodoPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMetodoPago", fetch = FetchType.LAZY)
    private List<Pagos> pagosList;

    public MetodosPago() {
    }

    public MetodosPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public MetodosPago(Integer idMetodoPago, String nombre) {
        this.idMetodoPago = idMetodoPago;
        this.nombre = nombre;
    }

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMetodoPago != null ? idMetodoPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodosPago)) {
            return false;
        }
        MetodosPago other = (MetodosPago) object;
        if ((this.idMetodoPago == null && other.idMetodoPago != null) || (this.idMetodoPago != null && !this.idMetodoPago.equals(other.idMetodoPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.MetodosPago[ idMetodoPago=" + idMetodoPago + " ]";
    }
    
}
