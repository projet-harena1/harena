package com.harena.api.model.possession;


import com.harena.api.model.Devise;

import java.time.LocalDate;

import static com.harena.api.model.Devise.NON_NOMMEE;


public final class Dette extends Argent {

    public Dette(String nom, LocalDate t, int valeurComptable, Devise devise) {
        super(nom, t, valeurComptable, devise);
        if (valeurComptable > 0) {
            throw new IllegalArgumentException();
        }
    }

    public Dette(String nom, LocalDate t, int valeurComptable) {
        this(nom, t, valeurComptable, NON_NOMMEE);
    }

    private Dette(Argent argent) {
        this(argent.nom, argent.t, argent.valeurComptable, argent.devise);
    }

    @Override
    public Dette projectionFuture(LocalDate tFutur) {
        return new Dette(super.projectionFuture(tFutur));
    }
}