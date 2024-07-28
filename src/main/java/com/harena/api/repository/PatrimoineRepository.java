package com.harena.api.repository;

import com.harena.api.repository.model.Patrimoine;
import com.harena.api.repository.utils.IOFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrimoineRepository extends IOFileInterface<Patrimoine> {
    Patrimoine findPatrimoineByNom(String nom);
}
