package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class ProductoResponse {
    private Integer idProducto;
    private String codigoBarras;
    private String nombreDescripcion;
    private BigDecimal precioVenta;
    private Integer stock;
    private Integer stockMinimo;
    private String imagenUrl;
    private Boolean manejaLote;
    private BigDecimal precioMayorista;
    private BigDecimal precioDistribuidor;
    private String codigoUnico;
    private CategoriaResponse idCategoria;
    private EstadoResponse idEstado;
    private MedidaResponse idUnidadMedida;

}
