package com.harena.api.dto.json;

import com.harena.api.enums.ArgentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArgentDataFromJsonFile {
    private Long possessionId;
    private LocalDate dateDouverure;
    private ArgentType type;
}
