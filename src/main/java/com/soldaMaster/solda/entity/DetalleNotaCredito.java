package com.soldaMaster.solda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_nota_credito")
@Getter
@Setter
@NoArgsConstructor
public class DetalleNotaCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_nota")
    private Integer idDetalleNota;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @NotNull
    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @NotNull
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lote", referencedColumnName = "id_lote")
    private Lotes idLote;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_nota", referencedColumnName = "id_nota")
    private NotasCredito idNota;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = false)
    private Productos idProducto;

}