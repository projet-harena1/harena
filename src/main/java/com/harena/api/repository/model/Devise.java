package com.harena.api.repository.model;

import java.io.Serializable;
import java.time.LocalDate;


public record Devise(String nom, String code) implements Serializable {
    public static final Devise NON_NOMMEE = new Devise("non-nommee", "NON_NOMMEE");

    public double valeurEnAriary(LocalDate tFutur) {
        return 1;
    }
}
