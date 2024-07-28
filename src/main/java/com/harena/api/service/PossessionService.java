package com.harena.api.service;

import com.harena.api.dto.json.PossessionDataJsonFile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface PossessionService {
    List<PossessionDataJsonFile> findAllPossessions();

    Optional<PossessionDataJsonFile> createPossession(PossessionDataJsonFile possession);

    Optional<PossessionDataJsonFile> updatePossession(PossessionDataJsonFile possession);

    boolean deletePossession(Long id);

    int calculateFutureValue(Long id, LocalDate futureDate);
}
