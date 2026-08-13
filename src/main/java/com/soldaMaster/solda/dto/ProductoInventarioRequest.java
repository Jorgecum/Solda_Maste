package com.soldaMaster.solda.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoInventarioRequest {
    private ProductoRequest producto;
    private LoteRequest lote;
    private CertificadoRequest certificado;
}
