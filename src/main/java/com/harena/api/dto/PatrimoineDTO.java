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
public class PatrimoineDTO {
  private String nom;
  private String personNom;
  private LocalDate t;
}
