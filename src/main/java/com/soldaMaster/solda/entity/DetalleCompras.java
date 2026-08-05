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
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_compras")
@Getter
@Setter
@NoArgsConstructor
public class DetalleCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra")
    private Integer idDetalleCompra;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_costo_unitario")
    private BigDecimal precioCostoUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra", referencedColumnName = "id_compra")
    private Compras idCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lote", referencedColumnName = "id_lote")
    private Lotes idLote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Productos idProducto;

}