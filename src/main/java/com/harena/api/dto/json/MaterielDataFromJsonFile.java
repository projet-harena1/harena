package com.harena.api.dto.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterielDataFromJsonFile {
    private Long possessionId;
    private LocalDate dateDAcquisition;
    private Double tauxDappreciationAnnuel;
}
