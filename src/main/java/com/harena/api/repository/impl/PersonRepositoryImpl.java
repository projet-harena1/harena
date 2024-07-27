package com.harena.api.repository.impl;

import com.harena.api.dto.json.PersonneDataJsonFile;
import com.harena.api.repository.PersonRepository;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl extends BaseRepository<PersonneDataJsonFile> implements PersonRepository {
    public PersonRepositoryImpl(
            ReadDataFromJsonFile<PersonneDataJsonFile> readDataFromJsonFile,
            WriteDataToJsonFile<PersonneDataJsonFile> writeDataToJsonFile,
            @Value("${path.to.file.person}") String filePath) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }


    @Override
    public List<PersonneDataJsonFile> loadAllData() {
        return super.loadAllData(PersonneDataJsonFile.class);
    }

    @Override
    public Optional<PersonneDataJsonFile> create(PersonneDataJsonFile toCreate) {
        return super.create(PersonneDataJsonFile.class, toCreate);
    }

    @Override
    public Optional<PersonneDataJsonFile> update(PersonneDataJsonFile toUpdated) {
        return super.update(PersonneDataJsonFile.class, toUpdated);
    }
}
