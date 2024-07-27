package com.harena.api.repository;

import com.harena.api.dto.json.MaterielDataJsonFile;
import com.harena.api.repository.utils.LoadDataFromJsonFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterielRepository extends LoadDataFromJsonFileInterface<MaterielDataJsonFile> {
}
