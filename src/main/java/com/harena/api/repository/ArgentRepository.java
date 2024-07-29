package com.harena.api.repository;

import com.harena.api.repository.model.possession.Argent;
import com.harena.api.repository.utils.IOFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface ArgentRepository extends IOFileInterface<Argent> {
    Argent findArgentByNomAndPatrimoine(String argentNom, String patrimoineNom);

    Argent findArgentByNom(String nom);

    void delete(Argent argent);
}
