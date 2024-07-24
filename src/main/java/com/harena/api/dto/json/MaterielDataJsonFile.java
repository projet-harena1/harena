package com.harena.api.dto.json;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterielDataJsonFile {
  private Long possessionId;
  private LocalDate dateDAcquisition;
  private Double tauxDappreciationAnnuel;
}
