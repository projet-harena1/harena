package com.harena.api.repository.impl;

import com.harena.api.dto.json.ArgentDataJsonFile;
import com.harena.api.repository.ArgentRepository;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArgentRepositoryImpl extends BaseRepository<ArgentDataJsonFile> implements ArgentRepository {

    public ArgentRepositoryImpl(
            ReadDataFromJsonFile<ArgentDataJsonFile> readDataFromJsonFile,
            WriteDataToJsonFile<ArgentDataJsonFile> writeDataToJsonFile,
            @Value("${path.to.file.argent}") String filePath
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }

    @Override
    public List<ArgentDataJsonFile> loadAllData() {
        return super.loadAllData(ArgentDataJsonFile.class);
    }

    @Override
    public Optional<ArgentDataJsonFile> create(ArgentDataJsonFile toCreate) {
        return super.create(ArgentDataJsonFile.class, toCreate);
    }

    @Override
    public Optional<ArgentDataJsonFile> update(ArgentDataJsonFile toUpdate) {
        return super.update(ArgentDataJsonFile.class, toUpdate);
    }
}
