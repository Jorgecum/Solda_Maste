package com.soldaMaster.solda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductosRelacionadosPK implements Serializable {

    @NotNull
    @Column(name = "id_producto", nullable = false)
    private int idProducto;

    @NotNull
    @Column(name = "id_producto_relacionado", nullable = false)
    private int idProductoRelacionado;

}