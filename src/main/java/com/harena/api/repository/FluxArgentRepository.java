package com.harena.api.repository;

import com.harena.api.dto.json.FluxArgentDataJsonFile;
import com.harena.api.repository.utils.LoadDataFromJsonFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface FluxArgentRepository extends LoadDataFromJsonFileInterface<FluxArgentDataJsonFile> {
}
