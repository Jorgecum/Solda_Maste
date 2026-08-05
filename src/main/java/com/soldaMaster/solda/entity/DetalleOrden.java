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
@Table(name = "detalle_orden")
@Getter
@Setter
@NoArgsConstructor
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_orden")
    private Integer idDetalleOrden;

    @Column(name = "cantidad_pedida")
    private Integer cantidadPedida;

    @Column(name = "precio_unitario_pactado")
    private BigDecimal precioUnitarioPactado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden")
    private OrdenesCompra idOrden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Productos idProducto;

}