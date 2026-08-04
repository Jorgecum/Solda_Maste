package com.soldaMaster.solda.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "metodos_pago")
@Getter
@Setter
@NoArgsConstructor
public class MetodosPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo_pago")
    private Integer idMetodoPago;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMetodoPago", fetch = FetchType.LAZY)
    private List<Pagos> pagosList;

}