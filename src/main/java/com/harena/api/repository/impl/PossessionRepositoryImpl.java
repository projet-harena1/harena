package com.harena.api.repository.impl;

import com.harena.api.dto.json.PossessionDataJsonFile;
import com.harena.api.repository.PossessionRepository;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PossessionRepositoryImpl  extends BaseRepository<PossessionDataJsonFile> implements PossessionRepository {
    public PossessionRepositoryImpl(
            ReadDataFromJsonFile<PossessionDataJsonFile> readDataFromJsonFile,
            WriteDataToJsonFile<PossessionDataJsonFile> writeDataToJsonFile,
            @Value("${path.to.file.possession}") String filePath
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }

    @Override
    public List<PossessionDataJsonFile> loadAllData() {
        return super.loadAllData(PossessionDataJsonFile.class);
    }

    @Override
    public Optional<PossessionDataJsonFile> create(PossessionDataJsonFile toCreate) {
        return super.create(PossessionDataJsonFile.class, toCreate);
    }

    @Override
    public Optional<PossessionDataJsonFile> update(PossessionDataJsonFile toUpdated) {
        return super.create(PossessionDataJsonFile.class, toUpdated);
    }
}
