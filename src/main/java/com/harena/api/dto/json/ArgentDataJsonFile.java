package com.harena.api.dto.json;

import com.harena.api.enums.ArgentType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArgentDataJsonFile {
  private Long possessionId;
  private LocalDate dateDouverture;
  private ArgentType type;
}
