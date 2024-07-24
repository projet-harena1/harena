package com.harena.api.repository;

import com.harena.api.dto.json.PatrimoineDataJsonFile;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrimoineRepository extends LoadDataFromJsonFileInterface<PatrimoineDataJsonFile> {
}
