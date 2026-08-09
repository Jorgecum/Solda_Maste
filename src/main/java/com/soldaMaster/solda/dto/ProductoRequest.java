package com.soldaMaster.solda.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class ProductoRequest {

     @Size(max = 100, message = "El código de barras no puede superar 100 caracteres")
    private String codigoBarras;
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombreDescripcion;
    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.0", message = "El precio de venta no puede ser negativo")
    private BigDecimal precioVenta;
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer stockMinimo;
    private String imagenUrl;
    private Boolean manejaLote;
    @DecimalMin(value = "0.0", message = "El precio mayorista no puede ser negativo")
    private BigDecimal precioMayorista;
    @DecimalMin(value = "0.0", message = "El precio distribuidor no puede ser negativo")
    private BigDecimal precioDistribuidor;
    @NotBlank(message = "El codigo de barras es obligatorio")
    @Size(max = 10, message = "El código único no puede superar 10 caracteres")
    private String codigoUnico;
    @NotNull(message = "La categoria es obligatoria")
    private Integer idCategoria;
    @NotNull(message = "El estado es obligatorio")
    private Integer idEstado;
    @NotNull(message = "La medida es obligatoria")
    private Integer idUnidadMedida;
}
