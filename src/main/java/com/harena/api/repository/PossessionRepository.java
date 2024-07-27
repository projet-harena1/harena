package com.harena.api.repository;

import com.harena.api.dto.json.PossessionDataJsonFile;
import com.harena.api.repository.utils.LoadDataFromJsonFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface PossessionRepository extends LoadDataFromJsonFileInterface<PossessionDataJsonFile> {
}
