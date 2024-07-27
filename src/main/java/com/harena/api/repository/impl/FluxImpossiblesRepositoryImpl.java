package com.harena.api.repository.impl;

import com.harena.api.dto.json.FluxImpossiblesDataJsonFile;
import com.harena.api.repository.FluxImpossiblesRepository;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FluxImpossiblesRepositoryImpl extends BaseRepository<FluxImpossiblesDataJsonFile> implements FluxImpossiblesRepository {
    public FluxImpossiblesRepositoryImpl(
            ReadDataFromJsonFile<FluxImpossiblesDataJsonFile> readDataFromJsonFile,
            WriteDataToJsonFile<FluxImpossiblesDataJsonFile> writeDataToJsonFile,
            @Value("${path.to.file.flux.impossibles}") String filePath
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }

    @Override
    public List<FluxImpossiblesDataJsonFile> loadAllData() {
        return super.loadAllData(FluxImpossiblesDataJsonFile.class);
    }

    @Override
    public Optional<FluxImpossiblesDataJsonFile> create(FluxImpossiblesDataJsonFile toCreate) {
        return super.create(FluxImpossiblesDataJsonFile.class, toCreate);
    }

    @Override
    public Optional<FluxImpossiblesDataJsonFile> update(FluxImpossiblesDataJsonFile toUpdated) {
        return super.update(FluxImpossiblesDataJsonFile.class, toUpdated);
    }
}
