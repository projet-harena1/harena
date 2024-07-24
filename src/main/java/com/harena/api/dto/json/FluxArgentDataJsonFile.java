package com.harena.api.dto.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FluxArgentDataJsonFile {
    private Long possessionId;
    private Long argentId;
    private LocalDate debut;
    private LocalDate fin;
    private Integer fluxMensuel;
    private Integer dateDOperation;
}
