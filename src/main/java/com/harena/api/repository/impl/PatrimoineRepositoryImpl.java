package com.harena.api.repository.impl;

import com.harena.api.dto.json.PatrimoineDataJsonFile;
import com.harena.api.repository.PatrimoineRepository;
import com.harena.api.repository.ReadDataFromJsonFile;
import com.harena.api.repository.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Repository
public class PatrimoineRepositoryImpl implements PatrimoineRepository {
    private final ReadDataFromJsonFile<PatrimoineDataJsonFile> readDataFromJsonFile;
    private final WriteDataToJsonFile<PatrimoineDataJsonFile> writeDataToJsonFile;
    private final Path path;

    public PatrimoineRepositoryImpl(
            ReadDataFromJsonFile<PatrimoineDataJsonFile> readDataFromJsonFile,
            WriteDataToJsonFile<PatrimoineDataJsonFile> writeDataToJsonFile,
            @Value("${path.to.file.patrimoine}") String filePath
    ) {
        this.readDataFromJsonFile = readDataFromJsonFile;
        this.writeDataToJsonFile = writeDataToJsonFile;
        this.path = Paths.get(filePath);
    }

    @Override
    public List<PatrimoineDataJsonFile> loadAllData() {
        return readDataFromJsonFile.apply(PatrimoineDataJsonFile.class).apply(path);
    }

    @Override
    public Optional<PatrimoineDataJsonFile> create(PatrimoineDataJsonFile toCreate) {
        var data = this.loadAllData();
        if (data.stream().noneMatch(p -> p.equals(toCreate))) {
            data.add(toCreate);
            writeDataToJsonFile.apply(data, path);
            return Optional.of(toCreate);
        }
        return Optional.empty();
    }

    @Override
    public Optional<PatrimoineDataJsonFile> update(PatrimoineDataJsonFile toUpdated) {
        var data = this.loadAllData();
        var idx = data.indexOf(toUpdated);
        if (idx != -1) {
            data.set(idx, toUpdated);
            writeDataToJsonFile.apply(data, path);
            return Optional.of(toUpdated);
        }
        return Optional.empty();
    }
}
