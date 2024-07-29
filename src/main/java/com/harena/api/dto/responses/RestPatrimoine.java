package com.harena.api.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harena.api.repository.model.Personne;
import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class RestPatrimoine {
  @JsonProperty("nom")
  private String nom;

  @JsonProperty("t")
  private LocalDate t;

  @JsonProperty("possesseur")
  private Personne possesseur;

  @JsonProperty("valeur_comptable")
  private Integer valeurComptable;
}
