package com.harena.api.repository;

import com.harena.api.dto.json.TransfertArgentDataJsonFile;
import com.harena.api.repository.utils.LoadDataFromJsonFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferArgentRepository extends LoadDataFromJsonFileInterface<TransfertArgentDataJsonFile> {
}
