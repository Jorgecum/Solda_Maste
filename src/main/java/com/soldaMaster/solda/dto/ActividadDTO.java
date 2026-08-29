package com.soldaMaster.solda.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ActividadDTO {
    private String time;      
    private String label;      
    private String desc;
    @JsonIgnore
    private LocalDateTime fechaFiltro;   
}
