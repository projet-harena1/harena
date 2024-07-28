package com.harena.api.dto;


import com.harena.api.enums.ArgentType;
import lombok.*;

import java.time.LocalDate;

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
    private String deviseCode;
    private LocalDate dateOuverture;
    private ArgentType type;
}
