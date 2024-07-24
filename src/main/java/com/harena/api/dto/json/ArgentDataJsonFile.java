package com.harena.api.dto.json;

import com.harena.api.enums.ArgentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArgentDataJsonFile {
    private Long possessionId;
    private LocalDate dateDouverture;
    private ArgentType type;
}
