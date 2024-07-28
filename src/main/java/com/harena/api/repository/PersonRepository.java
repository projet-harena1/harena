package com.harena.api.repository;

import com.harena.api.repository.model.Personne;
import com.harena.api.repository.utils.IOFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends IOFileInterface<Personne> {
    Personne findPersonByNom(String nom);
}
