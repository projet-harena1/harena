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
public class FluxImpossibleDTO {
  private LocalDate date;
  private String nomArgent;
  private Integer valeurArgent;
}
