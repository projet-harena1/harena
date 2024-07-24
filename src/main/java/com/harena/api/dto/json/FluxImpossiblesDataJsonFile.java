package com.harena.api.dto.json;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FluxImpossiblesDataJsonFile {
  private String nomArgent;
  private LocalDate date;
  private Integer valeurArgent;
}
