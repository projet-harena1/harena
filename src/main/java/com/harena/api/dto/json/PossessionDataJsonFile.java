package com.harena.api.dto.json;

import com.harena.api.enums.PossessionType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PossessionDataJsonFile {
  private Long id;
  private PossessionType type;
  private LocalDate t;
  private String nom;
  private Integer valeurComptable;
  private String deviseNom;
  private String deviseCode;
  private String patrimoineNom;
}
