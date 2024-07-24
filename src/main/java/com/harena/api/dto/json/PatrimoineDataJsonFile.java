package com.harena.api.dto.json;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatrimoineDataJsonFile {
  private String nom;
  private String possesseurNom;
  private LocalDate t;
  private Integer valeurComptable;
}
