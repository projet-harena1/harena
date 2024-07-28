package com.harena.api.repository.model.possession;


import com.harena.api.repository.model.Devise;
import lombok.Getter;

import java.time.LocalDate;

import static com.harena.api.repository.model.Devise.NON_NOMMEE;
import static java.lang.Math.max;
import static java.time.temporal.ChronoUnit.DAYS;

@Getter
public final class Materiel extends Possession {
    private final LocalDate dateAcquisition;
    private final double tauxDAppreciationAnnuelle;

    public Materiel(
            String nom, LocalDate t, int valeurComptable, LocalDate dateAcquisition, double tauxDAppreciationAnnuelle, Devise devise) {
        super(nom, t, valeurComptable, devise);
        this.dateAcquisition = dateAcquisition;
        this.tauxDAppreciationAnnuelle = tauxDAppreciationAnnuelle;
    }

    public Materiel(
            String nom, LocalDate t, int valeurComptable, LocalDate dateAcquisition, double tauxDAppreciationAnnuelle) {
        this(nom, t, valeurComptable, dateAcquisition, tauxDAppreciationAnnuelle, NON_NOMMEE);
    }

    @Override
    public Possession projectionFuture(LocalDate tFutur) {
        if (tFutur.isBefore(dateAcquisition)) {
            return new Materiel(nom, tFutur, 0, dateAcquisition, tauxDAppreciationAnnuelle, devise);
        }
        var joursEcoules = DAYS.between(t, tFutur);
        double valeurAjouteeJournaliere = valeurComptable * (tauxDAppreciationAnnuelle / 365.);
        int valeurComptableFuture = max(0, (int) (valeurComptable + valeurAjouteeJournaliere * joursEcoules));
        return new Materiel(nom, tFutur, valeurComptableFuture, dateAcquisition, tauxDAppreciationAnnuelle, devise);
    }
}
