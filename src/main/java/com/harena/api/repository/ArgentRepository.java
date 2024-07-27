package com.harena.api.repository;

import com.harena.api.dto.json.ArgentDataJsonFile;
import com.harena.api.repository.utils.LoadDataFromJsonFileInterface;
import org.springframework.stereotype.Repository;

@Repository
public interface ArgentRepository extends LoadDataFromJsonFileInterface<ArgentDataJsonFile> {
}
