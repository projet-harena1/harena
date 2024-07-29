package com.harena.api.repository.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiFunction;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class WriteDataToJsonFile<T> implements BiFunction<List<T>, Path, Boolean> {
  private final ObjectMapper objectMapper;

  public WriteDataToJsonFile(ObjectMapper objectMapper) {
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
    @Cleanup var outputStream = Files.newOutputStream(path);
    objectMapper.writeValue(outputStream, data);
    return true;
  }
}
