package com.harena.api.repository.model;

import com.harena.api.repository.model.possession.FluxArgent;
import java.time.LocalDate;
import java.util.Set;

public record FluxImpossibles(
    LocalDate date, String nomArgent, int valeurArgent, Set<FluxArgent> flux) {}
