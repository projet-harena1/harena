package com.harena.api.dto;

import com.harena.api.enums.ArgentType;
import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class ArgentDTO {
  private String nom;
  private LocalDate t;
  private String patrimoineNom;
  private Integer valeurComptable;
  private DeviseDTO devise;
  private LocalDate dateOuverture;
  private ArgentType type;
}
