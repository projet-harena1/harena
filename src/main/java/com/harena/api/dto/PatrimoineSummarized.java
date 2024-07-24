package com.harena.api.dto;

import com.harena.api.model.Personne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatrimoineSummarized {
  private String nom;
  private LocalDate t;
  private Personne possesseur;
  private Integer valeurComptable;
}
