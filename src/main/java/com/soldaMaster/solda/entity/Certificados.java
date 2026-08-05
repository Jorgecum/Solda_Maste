package com.soldaMaster.solda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "certificados")
@Getter
@Setter
@NoArgsConstructor
public class Certificados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_certificado")
    private Integer idCertificado;

    @Size(max = 100)
    @Column(name = "numero", length = 100)
    private String numero;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "archivo_url")
    private String archivoUrl;

    @OneToMany(mappedBy = "idCertificado", fetch = FetchType.LAZY)
    private List<Lotes> lotesList;

}