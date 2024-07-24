package com.harena.api.dto.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransfertArgentDataJsonFile {
    private String nom;
    private Long depuisArgentId;
    private Long versArgentId;
    private LocalDate debut;
    private LocalDate fin;
    private Integer fluxMensuel;
    private Integer dateOperation;
    private String deviseNom;
    private String deviseCode;
}
