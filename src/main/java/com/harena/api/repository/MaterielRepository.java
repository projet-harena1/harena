package com.harena.api.repository;

import com.harena.api.repository.model.possession.Materiel;
import com.harena.api.repository.utils.IOFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterielRepository extends IOFileInterface<Materiel> {
    Materiel findMaterielByNom(String nom);

    void delete(Materiel materiel);
}
