package com.harena.api.model.possession;

import static com.harena.api.model.Devise.NON_NOMMEE;

import com.harena.api.model.Devise;
import java.time.LocalDate;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString(callSuper = true)
@Slf4j
@Getter
public final class FluxArgent extends Possession {
  @ToString.Exclude private final Argent argent;
  private final LocalDate debut;
  private final LocalDate fin;
  private final int fluxMensuel;
  private final int dateOperation;

  public FluxArgent(
      String nom,
      Argent argent,
      LocalDate debut,
      LocalDate fin,
      int fluxMensuel,
      int dateOperation,
      Devise devise) {
    super(nom, null, 0, devise);
    this.argent = argent;
    this.argent.addFinancés(this);

    this.debut = debut;
    this.fin = fin;
    this.fluxMensuel = fluxMensuel;
    this.dateOperation = dateOperation;
  }

  public FluxArgent(
      String nom,
      Argent argent,
      LocalDate debut,
      LocalDate fin,
      int fluxMensuel,
      int dateOperation) {
    this(nom, argent, debut, fin, fluxMensuel, dateOperation, NON_NOMMEE);
  }

  public FluxArgent(String nom, Argent argent, LocalDate date, int montant) {
    this(nom, argent, date, date, montant, date.getDayOfMonth());
  }

  @Override
  public FluxArgent projectionFuture(LocalDate tFutur) {
    var tFuturMajoréParFin = (tFutur.isBefore(fin)) ? tFutur : fin;
    var debutOperationMinoréParDebut = argent.t.isBefore(debut) ? debut : argent.t;
    if (debutOperationMinoréParDebut.isAfter(tFuturMajoréParFin)) {
      return this;
    }

    var nbOperations =
        (int)
            debutOperationMinoréParDebut
                .datesUntil(tFuturMajoréParFin.plusDays(1))
                .filter(d -> d.getDayOfMonth() == dateOperation)
                .count();
    var valeurFutur = argent.getValeurComptable() + fluxMensuel * nbOperations;
    var argentFutur =
        new Argent(
            argent.nom + " réduit au financement de " + this, tFutur, valeurFutur, argent.devise);
    return new FluxArgent(
        nom, argentFutur, debut, tFuturMajoréParFin, fluxMensuel, dateOperation, devise);
  }
}
