package com.harena.api.repository.impl;

import com.harena.api.dto.json.DeviseDataJsonFile;
import com.harena.api.dto.json.FluxArgentDataJsonFile;
import com.harena.api.repository.FluxArgentRepository;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class FluxArgentRepositoryImpl extends BaseRepository<FluxArgentDataJsonFile> implements FluxArgentRepository {
    public FluxArgentRepositoryImpl(ReadDataFromJsonFile<FluxArgentDataJsonFile> readDataFromJsonFile, WriteDataToJsonFile<FluxArgentDataJsonFile> writeDataToJsonFile, @Value("${path.to.file.fluxargent}") String filePath) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }

    public List<FluxArgentDataJsonFile> loadAllData() {
        return super.loadAllData(FluxArgentDataJsonFile.class);
    }

    public Optional<FluxArgentDataJsonFile> create(FluxArgentDataJsonFile toCreate) {
        return super.create(FluxArgentDataJsonFile.class, toCreate);
    }

    public Optional<FluxArgentDataJsonFile> update(FluxArgentDataJsonFile toUpdate) {
        return super.update(FluxArgentDataJsonFile.class, toUpdate);
    }
}
