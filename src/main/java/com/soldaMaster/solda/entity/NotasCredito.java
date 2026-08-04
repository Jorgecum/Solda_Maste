package com.soldaMaster.solda.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notas_credito")
@Getter
@Setter
@NoArgsConstructor
public class NotasCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota")
    private Integer idNota;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "serie_correlativa", length = 50, nullable = false)
    private String serieCorrelativa;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(name = "motivo")
    private String motivo;

    @NotNull
    @Column(name = "monto_total", nullable = false)
    private BigDecimal montoTotal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNota", fetch = FetchType.LAZY)
    private List<DetalleNotaCredito> detalleNotaCreditoList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta", nullable = false)
    private Ventas idVenta;

}