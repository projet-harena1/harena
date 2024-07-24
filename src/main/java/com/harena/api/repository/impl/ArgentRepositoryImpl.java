package com.harena.api.repository.impl;

import com.harena.api.dto.json.ArgentDataJsonFile;
import com.harena.api.repository.ArgentRepository;
import com.harena.api.repository.ReadDataFromJsonFile;
import com.harena.api.repository.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Repository
public class ArgentRepositoryImpl implements ArgentRepository {
    private final ReadDataFromJsonFile<ArgentDataJsonFile> readDataFromJsonFile;
    private final WriteDataToJsonFile<ArgentDataJsonFile> writeDataToJsonFile;
    private final Path filePath;

    public ArgentRepositoryImpl(
            ReadDataFromJsonFile<ArgentDataJsonFile> readDataFromJsonFile,
            WriteDataToJsonFile<ArgentDataJsonFile> writeDataToJsonFile,
            @Value("${path.to.file.argent}") String filePath
    ) {
        this.readDataFromJsonFile = readDataFromJsonFile;
        this.writeDataToJsonFile = writeDataToJsonFile;
        this.filePath = Paths.get(filePath);
    }

    @Override
    public List<ArgentDataJsonFile> loadAllData() {
        return readDataFromJsonFile
                .apply(ArgentDataJsonFile.class)
                .apply(filePath);
    }

    @Override
    public Optional<ArgentDataJsonFile> create(ArgentDataJsonFile toCreate) {
        var currentData = readDataFromJsonFile
                .apply(ArgentDataJsonFile.class)
                .apply(filePath);
        currentData.add(toCreate);
        writeDataToJsonFile.apply(currentData, filePath);
        return Optional.of(toCreate);
    }

    @Override
    public Optional<ArgentDataJsonFile> update(ArgentDataJsonFile toUpdate) {
        var currentData = readDataFromJsonFile.apply(ArgentDataJsonFile.class).apply(filePath);
        var index = currentData.indexOf(toUpdate);
        if (index != -1) {
            currentData.set(index, toUpdate);
            writeDataToJsonFile.apply(currentData, filePath);
            return Optional.of(toUpdate);
        }
        return Optional.empty();
    }

}
