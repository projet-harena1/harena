package com.harena.api.repository.impl;

import com.harena.api.dto.json.PersonneDataJsonFile;
import com.harena.api.repository.PersonRepository;
import com.harena.api.repository.ReadDataFromJsonFile;
import com.harena.api.repository.WriteDataToJsonFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
  private final ReadDataFromJsonFile<PersonneDataJsonFile> readDataFromJsonFile;
  private final WriteDataToJsonFile<PersonneDataJsonFile> writeDataToJsonFile;
  private final Path filePath;

  public PersonRepositoryImpl(
      ReadDataFromJsonFile<PersonneDataJsonFile> readDataFromJsonFile,
      WriteDataToJsonFile<PersonneDataJsonFile> writeDataToJsonFile,
      @Value("${path.to.file.person}") String filePath) {
    this.readDataFromJsonFile = readDataFromJsonFile;
    this.writeDataToJsonFile = writeDataToJsonFile;
    this.filePath = Paths.get(filePath);
  }

  @Override
  public List<PersonneDataJsonFile> loadAllData() {
    return readDataFromJsonFile.apply(PersonneDataJsonFile.class).apply(filePath);
  }

  @Override
  public Optional<PersonneDataJsonFile> create(PersonneDataJsonFile toCreate) {
    var data = loadAllData();
    if (data.stream().noneMatch(p -> p.equals(toCreate))) {
      data.add(toCreate);
      writeDataToJsonFile.apply(data, filePath);
      return Optional.of(toCreate);
    }
    return Optional.empty();
  }

  @Override
  public Optional<PersonneDataJsonFile> update(PersonneDataJsonFile toUpdated) {
    var data = loadAllData();
    var idx = data.indexOf(toUpdated);
    if (idx != -1) {
      data.set(idx, toUpdated);
      writeDataToJsonFile.apply(data, filePath);
      return Optional.of(toUpdated);
    }
    return Optional.empty();
  }
}
