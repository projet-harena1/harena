package com.harena.api.repository.impl;

import com.harena.api.dto.json.PatrimoineDataJsonFile;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PatrimoineRepository extends BaseRepository<PatrimoineDataJsonFile> implements com.harena.api.repository.PatrimoineRepository {

    public PatrimoineRepository(
            ReadDataFromJsonFile<PatrimoineDataJsonFile> readDataFromJsonFile,
            WriteDataToJsonFile<PatrimoineDataJsonFile> writeDataToJsonFile,
            @Value("${path.to.file.patrimoine}") String filePath
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }

    @Override
    public List<PatrimoineDataJsonFile> loadAllData() {
        return super.loadAllData(PatrimoineDataJsonFile.class);
    }

    @Override
    public Optional<PatrimoineDataJsonFile> create(PatrimoineDataJsonFile toCreate) {
       return super.create(PatrimoineDataJsonFile.class, toCreate);
    }

    @Override
    public Optional<PatrimoineDataJsonFile> update(PatrimoineDataJsonFile toUpdated) {
        return super.update(PatrimoineDataJsonFile.class, toUpdated);
    }
}
