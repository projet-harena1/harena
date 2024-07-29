package com.harena.api.repository.model.possession;

import static com.harena.api.repository.model.Devise.NON_NOMMEE;
import static java.util.stream.Collectors.toSet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harena.api.repository.model.Devise;
import com.harena.api.repository.model.Patrimoine;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public sealed class Argent extends Possession permits Dette, Creance {
  private final LocalDate dateOuverture;
  private final Set<FluxArgent> fluxArgents;

  @JsonCreator
  public Argent(
      @JsonProperty("nom") String nom,
      @JsonProperty("date_d_ouverture") LocalDate dateOuverture,
      @JsonProperty("t") LocalDate t,
      @JsonProperty("valeur_comptable") int valeurComptable,
      @JsonProperty("flux_argents") Set<FluxArgent> fluxArgents,
      @JsonProperty("devise") Devise devise) {
    super(nom, t, valeurComptable, devise);
    this.fluxArgents = fluxArgents != null ? fluxArgents : new HashSet<>();
    this.dateOuverture = dateOuverture;
  }

  public Argent(
      String nom,
      LocalDate t,
      int valeurComptable,
      Devise devise,
      Patrimoine patrimoine,
      LocalDate dateOuverture,
      Set<FluxArgent> fluxArgents) {
    super(nom, t, valeurComptable, devise, patrimoine);
    this.dateOuverture = dateOuverture;
    this.fluxArgents = fluxArgents;
  }

  public Argent(String nom, LocalDate t, int valeurComptable, Devise devise) {
    this(nom, t, t, valeurComptable, devise);
  }

  public Argent(
      String nom, LocalDate dateOuverture, LocalDate t, int valeurComptable, Devise devise) {
    this(nom, dateOuverture, t, valeurComptable, new HashSet<>(), devise);
  }

  public Argent(String nom, LocalDate t, int valeurComptable) {
    this(nom, t, t, valeurComptable);
  }

  public Argent(String nom, LocalDate dateOuverture, LocalDate t, int valeurComptable) {
    this(nom, dateOuverture, t, valeurComptable, new HashSet<>());
  }

  private Argent(
      String nom,
      LocalDate dateOuverture,
      LocalDate t,
      int valeurComptable,
      Set<FluxArgent> fluxArgents) {
    this(nom, dateOuverture, t, valeurComptable, fluxArgents, NON_NOMMEE);
  }

  @Override
  public Argent projectionFuture(LocalDate tFutur) {
    if (tFutur.isBefore(dateOuverture)) {
      return new Argent(nom, tFutur, 0, devise);
    }

    return new Argent(
        nom,
        dateOuverture,
        tFutur,
        valeurComptable - financementsFuturs(tFutur),
        fluxArgents.stream().map(f -> f.projectionFuture(tFutur)).collect(toSet()),
        devise);
  }

  private int financementsFuturs(LocalDate tFutur) {
    return fluxArgents.stream()
        .mapToInt(
            f -> valeurComptable - f.projectionFuture(tFutur).getArgent().getValeurComptable())
        .sum();
  }

  void addFinancés(FluxArgent fluxArgent) {
    fluxArgents.add(fluxArgent);
  }
}
