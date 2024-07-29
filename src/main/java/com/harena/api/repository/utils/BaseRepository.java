package com.harena.api.repository.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T> {
    protected final ReadDataFromJsonFile<T> readDataFromJsonFile;
    protected final WriteDataToJsonFile<T> writeDataToJsonFile;
    protected final Path filePath;

    protected BaseRepository(
            ReadDataFromJsonFile<T> readDataFromJsonFile,
            WriteDataToJsonFile<T> writeDataToJsonFile,
            String filePath
    ) {
        this.readDataFromJsonFile = readDataFromJsonFile;
        this.writeDataToJsonFile = writeDataToJsonFile;
        this.filePath = Paths.get(filePath);
    }

    public List<T> loadAllData(Class<T> clazz) {
        return readDataFromJsonFile.apply(clazz).apply(filePath);
    }

    public Optional<T> create(Class<T> clazz, T toCreate) {
        var currentData = loadAllData(clazz);
        if (currentData.stream().noneMatch(p -> p.equals(toCreate))) {
            currentData.add(toCreate);
            writeDataToJsonFile.apply(currentData, filePath);
            return Optional.of(toCreate);
        }
        return Optional.empty();
    }

    public Optional<T> update(Class<T> clazz, T toCreate, T toUpdate) {
        var currentData = loadAllData(clazz);
        var index = currentData.indexOf(toUpdate);
        if (index != -1) {
            currentData.set(index, toCreate);
            writeDataToJsonFile.apply(currentData, filePath);
            return Optional.of(toUpdate);
        }
        return Optional.empty();
    }

    public void delete(Class<T> clazz, T toDelete) {
        var currentData = loadAllData(clazz);
        var removed = currentData.removeIf(p -> p.equals(toDelete));
        if (removed) {
            writeDataToJsonFile.apply(currentData, filePath);
        }
    }
}
