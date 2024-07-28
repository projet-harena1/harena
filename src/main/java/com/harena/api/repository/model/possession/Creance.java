package com.harena.api.repository.model.possession;


import com.harena.api.repository.model.Devise;

import java.time.LocalDate;

import static com.harena.api.repository.model.Devise.NON_NOMMEE;


public final class Creance extends Argent {

    public Creance(String nom, LocalDate t, int valeurComptable, Devise devise) {
        super(nom, t, valeurComptable, devise);
        if (valeurComptable < 0) {
            throw new IllegalArgumentException();
        }
    }

    public Creance(String nom, LocalDate t, int valeurComptable) {
        this(nom, t, valeurComptable, NON_NOMMEE);
    }

    private Creance(Argent argent) {
        this(argent.nom, argent.t, argent.valeurComptable, argent.devise);
    }

    @Override
    public Creance projectionFuture(LocalDate tFutur) {
        return new Creance(super.projectionFuture(tFutur));
    }
}
