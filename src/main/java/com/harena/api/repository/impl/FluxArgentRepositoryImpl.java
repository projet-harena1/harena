package com.harena.api.repository.impl;

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
        return readDataFromJsonFile
                .apply(FluxArgentDataJsonFile.class)
                .apply(filePath);
    }

    public Optional<FluxArgentDataJsonFile> create(FluxArgentDataJsonFile toCreate) {
        var currentData = readDataFromJsonFile
                .apply(FluxArgentDataJsonFile.class)
                .apply(filePath);
        currentData.add(toCreate);
        writeDataToJsonFile.apply(currentData, filePath);
        return Optional.of(toCreate);
    }

    public Optional<FluxArgentDataJsonFile> update(FluxArgentDataJsonFile toUpdate) {
        var currentData = readDataFromJsonFile.apply(FluxArgentDataJsonFile.class).apply(filePath);
        var index = currentData.indexOf(toUpdate);
        if (index != -1) {
            currentData.set(index, toUpdate);
            writeDataToJsonFile.apply(currentData, filePath);
            return Optional.of(toUpdate);
        }
        return Optional.empty();
    }
}
