package com.harena.api.repository;

import com.harena.api.dto.json.PersonneDataJsonFile;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends LoadDataFromJsonFileInterface<PersonneDataJsonFile> {
}
