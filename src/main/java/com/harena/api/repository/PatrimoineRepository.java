package com.harena.api.repository;

import com.harena.api.model.Patrimoine;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatrimoineRepository {
    List<Patrimoine> findAllPatrimoines();
}
