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
public class FluxImpossibleDTO {
    private LocalDate date;
    private String nomArgent;
    private Integer valeurArgent;
}
