package com.harena.api.model;

import com.harena.api.model.possession.FluxArgent;
import java.time.LocalDate;
import java.util.Set;

public record FluxImpossibles(
    LocalDate date, String nomArgent, int valeurArgent, Set<FluxArgent> flux) {}
