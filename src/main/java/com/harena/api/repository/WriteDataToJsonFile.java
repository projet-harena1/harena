package com.harena.api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class WriteDataToJsonFile<T> implements BiFunction<List<T>, Path, Boolean> {
    private final ObjectMapper objectMapper;

    public WriteDataToJsonFile(@Qualifier("customObjectMapper") ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public Boolean apply(List<T> data, Path path) {
        if (Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        @Cleanup
        var outputStream = Files.newOutputStream(path);
        objectMapper.writeValue(outputStream, data);
        return true;
    }
}
