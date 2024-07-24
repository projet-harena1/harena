package com.harena.api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Component
public class ReadDataFromJsonFile<T> implements Function<Class<T>, Function<Path, List<T>>> {

    private final ObjectMapper objectMapper;

    public ReadDataFromJsonFile(
            @Qualifier("customObjectMapper") ObjectMapper objectMapper
    ) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Function<Path, List<T>> apply(Class<T> tClass) {
        return path -> readFromFile(tClass, path);
    }

    @SneakyThrows
    private List<T> readFromFile(Class<T> tClass, Path path) {
        if (Files.notExists(path)) {
            return Collections.emptyList();
        }
        @Cleanup
        var inputStream = Files.newInputStream(path);
        var collectionType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, tClass);
        return objectMapper.readValue(inputStream, collectionType);
    }
}
