package com.harena.api.repository;

import com.harena.api.repository.model.possession.FluxArgent;
import com.harena.api.repository.utils.IOFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface FluxArgentRepository extends IOFileInterface<FluxArgent> {
    FluxArgent findFluxArgentByNom(String nom);
    void delete(FluxArgent fluxArgent);
}
