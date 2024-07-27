package com.harena.api.repository;

import com.harena.api.dto.json.FluxImpossiblesFluxArgentsDataJsonFile;
import com.harena.api.repository.utils.LoadDataFromJsonFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface FluxImpossiblesFluxArgentRepository extends LoadDataFromJsonFileInterface<FluxImpossiblesFluxArgentsDataJsonFile> {
}
