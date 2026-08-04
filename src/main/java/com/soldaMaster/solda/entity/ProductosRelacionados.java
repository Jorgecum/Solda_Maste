package com.soldaMaster.solda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos_relacionados")
@Getter
@Setter
@NoArgsConstructor
public class ProductosRelacionados {

    @EmbeddedId
    protected ProductosRelacionadosPK productosRelacionadosPK;

    @Column(name = "frecuencia")
    private Integer frecuencia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    private Productos productos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto_relacionado", referencedColumnName = "id_producto", insertable = false, updatable = false)
    private Productos productos1;

}