package com.harena.api.repository;

import com.harena.api.repository.model.Devise;
import com.harena.api.repository.utils.IOFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviseRepository extends IOFileInterface<Devise> {
  Devise findDeviseByCode(String deviseCode);
}
