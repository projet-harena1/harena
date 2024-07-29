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
public class FluxArgentDTO {
  private String nom;
  private LocalDate t;
  private String patrimoineNom;
  private Integer valeurComptable;
  private DeviseDTO devise;
  private LocalDate debut;
  private LocalDate fin;
  private Integer dateOperation;
  private Integer fluxMensuel;
  private LocalDate fluxImpossiblesDate;
  private String argentNom;
}
