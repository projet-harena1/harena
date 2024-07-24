package com.harena.api.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harena.api.dto.json.ArgentDataFromJsonFile;
import com.harena.api.repository.ArgentRepository;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Repository
public class ArgentRepositoryImpl implements ArgentRepository {

    private final ObjectMapper objectMapper;
    private final Path filePath;

    public ArgentRepositoryImpl(
            @Qualifier("customObjectMapper") ObjectMapper objectMapper,
            @Value("${path.to.file}") String filePath
    ) {
        this.objectMapper = objectMapper;
        this.filePath = Paths.get(filePath);
    }


    @Override
    public List<ArgentDataFromJsonFile> loadAllData() {
        return readArgentDataFromFile();
    }

    @Override
    public Optional<ArgentDataFromJsonFile> create(ArgentDataFromJsonFile toCreate) {
        var currentData = readArgentDataFromFile();
        currentData.add(toCreate);
        writeArgentDataToFile(currentData);
        return Optional.of(toCreate);
    }

    @Override
    public Optional<ArgentDataFromJsonFile> update(ArgentDataFromJsonFile toUpdated) {
        var currentData = readArgentDataFromFile();
        var index = currentData.indexOf(toUpdated);
        if (index != -1) {
            currentData.set(index, toUpdated);
            writeArgentDataToFile(currentData);
            return Optional.of(toUpdated);
        }
        return Optional.empty();
    }

    @SneakyThrows
    private List<ArgentDataFromJsonFile> readArgentDataFromFile() {
        if (Files.notExists(filePath)) {
            return Collections.emptyList();
        }
        @Cleanup
        var inputStream = Files.newInputStream(filePath);
        return objectMapper.readValue(inputStream, new TypeReference<>() {
        });
    }

    @SneakyThrows
    private void writeArgentDataToFile(List<ArgentDataFromJsonFile> argentDTOs) {
        if (Files.notExists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
        @Cleanup
        var outputStream = Files.newOutputStream(filePath);
        objectMapper.writeValue(outputStream, argentDTOs);
    }
}
