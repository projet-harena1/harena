package com.harena.api.repository.impl;

import com.harena.api.dto.json.ArgentDataJsonFile;
import com.harena.api.dto.json.FluxArgentDataJsonFile;
import com.harena.api.repository.FluxArgentRepository;
import com.harena.api.repository.ReadDataFromJsonFile;
import com.harena.api.repository.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Repository
public class FluxArgentRepositoryImpl implements FluxArgentRepository {
    private final ReadDataFromJsonFile<FluxArgentDataJsonFile> readDataFromJsonFile;
    private final WriteDataToJsonFile<FluxArgentDataJsonFile> writeDataToJsonFile;
    private final Path filePath;

    public FluxArgentRepositoryImpl(ReadDataFromJsonFile<FluxArgentDataJsonFile> readDataFromJsonFile, WriteDataToJsonFile<FluxArgentDataJsonFile> writeDataToJsonFile, @Value("${path.to.file.fluxargent}") String filePath) {
        this.readDataFromJsonFile = readDataFromJsonFile;
        this.writeDataToJsonFile = writeDataToJsonFile;
        this.filePath = Paths.get(filePath);
    }

    @Override
    public List<FluxArgentDataJsonFile> loadAllData() {
        return readDataFromJsonFile
                .apply(FluxArgentDataJsonFile.class)
                .apply(filePath);
    }

    @Override
    public Optional<FluxArgentDataJsonFile> create(FluxArgentDataJsonFile toCreate) {
        var currentData = readDataFromJsonFile
                .apply(FluxArgentDataJsonFile.class)
                .apply(filePath);
        currentData.add(toCreate);
        writeDataToJsonFile.apply(currentData, filePath);
        return Optional.of(toCreate);
    }

    @Override
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
