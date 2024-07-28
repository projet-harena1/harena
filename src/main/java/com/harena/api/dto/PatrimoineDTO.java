package com.harena.api.dto;

import lombok.*;

import java.time.LocalDate;

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
