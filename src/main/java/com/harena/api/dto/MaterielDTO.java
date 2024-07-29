package com.harena.api.dto;

import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class MaterielDTO {
  private String nom;
  private LocalDate t;
  private String patrimoineNom;
  private Integer valeurComptable;
  private DeviseDTO devise;
  private LocalDate dateAcquisition;
  private Double tauxDAppreciationAnnuelle;
}
