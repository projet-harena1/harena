package com.harena.api.dto.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatrimoineDataFromJsonFile {
    private String nom;
    private String possesseurNom;
    private LocalDate t;
    private Integer valeurComptable;
}
