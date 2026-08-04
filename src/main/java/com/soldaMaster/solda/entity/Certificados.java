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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author augusto
 */
@Entity
@Table(name = "certificados")
@NamedQueries({
    @NamedQuery(name = "Certificados.findAll", query = "SELECT c FROM Certificados c"),
    @NamedQuery(name = "Certificados.findByIdCertificado", query = "SELECT c FROM Certificados c WHERE c.idCertificado = :idCertificado"),
    @NamedQuery(name = "Certificados.findByNumero", query = "SELECT c FROM Certificados c WHERE c.numero = :numero"),
    @NamedQuery(name = "Certificados.findByFechaEmision", query = "SELECT c FROM Certificados c WHERE c.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "Certificados.findByArchivoUrl", query = "SELECT c FROM Certificados c WHERE c.archivoUrl = :archivoUrl")})
public class Certificados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_certificado")
    private Integer idCertificado;
    @Size(max = 100)
    @Column(name = "numero")
    private String numero;
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    @Size(max = 2147483647)
    @Column(name = "archivo_url")
    private String archivoUrl;
    @OneToMany(mappedBy = "idCertificado", fetch = FetchType.LAZY)
    private List<Lotes> lotesList;

    public Certificados() {
    }

    public Certificados(Integer idCertificado) {
        this.idCertificado = idCertificado;
    }

    public Integer getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(Integer idCertificado) {
        this.idCertificado = idCertificado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getArchivoUrl() {
        return archivoUrl;
    }

    public void setArchivoUrl(String archivoUrl) {
        this.archivoUrl = archivoUrl;
    }

    public List<Lotes> getLotesList() {
        return lotesList;
    }

    public void setLotesList(List<Lotes> lotesList) {
        this.lotesList = lotesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCertificado != null ? idCertificado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificados)) {
            return false;
        }
        Certificados other = (Certificados) object;
        if ((this.idCertificado == null && other.idCertificado != null) || (this.idCertificado != null && !this.idCertificado.equals(other.idCertificado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soldaMaster.solda.entity.Certificados[ idCertificado=" + idCertificado + " ]";
    }
    
}
