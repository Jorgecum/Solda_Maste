package com.soldaMaster.solda.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CertificadoRequest {
    private String numero;
    private LocalDate fechaEmision;
    private String archivoUrl;
}
