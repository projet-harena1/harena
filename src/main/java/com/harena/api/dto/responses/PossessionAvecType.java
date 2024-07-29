package com.harena.api.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.harena.api.enums.PossessionType;
import com.harena.api.repository.model.possession.Argent;
import com.harena.api.repository.model.possession.FluxArgent;
import com.harena.api.repository.model.possession.Materiel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PossessionAvecType {
  private PossessionType type;
  private Argent argent;
  private Materiel materiel;
  private FluxArgent fluxArgent;
}
