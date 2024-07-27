package com.harena.api.repository;

import com.harena.api.dto.json.FluxImpossiblesDataJsonFile;
import com.harena.api.repository.utils.LoadDataFromJsonFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface FluxImpossiblesRepository extends LoadDataFromJsonFileInterface<FluxImpossiblesDataJsonFile> {
}
