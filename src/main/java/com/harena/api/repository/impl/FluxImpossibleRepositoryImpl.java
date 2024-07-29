package com.harena.api.repository.impl;

import com.harena.api.dto.FluxImpossibleDTO;
import com.harena.api.repository.FluxImpossibleRepository;
import com.harena.api.repository.model.FluxImpossibles;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class FluxImpossibleRepositoryImpl extends BaseRepository<FluxImpossibleDTO>
    implements FluxImpossibleRepository {
  public FluxImpossibleRepositoryImpl(
      ReadDataFromJsonFile<FluxImpossibleDTO> readDataFromJsonFile,
      WriteDataToJsonFile<FluxImpossibleDTO> writeDataToJsonFile,
      @Value("${path.to.file.flux.impossible}") String filePath) {
    super(readDataFromJsonFile, writeDataToJsonFile, filePath);
  }

  @Override
  public List<FluxImpossibles> loadAllData() {
    var fluxImpossibles = new ArrayList<FluxImpossibles>();
    super.loadAllData(FluxImpossibleDTO.class)
        .forEach(
            fluxImpossibleDTO ->
                fluxImpossibles.add(
                    new FluxImpossibles(
                        fluxImpossibleDTO.getDate(),
                        fluxImpossibleDTO.getNomArgent(),
                        fluxImpossibleDTO.getValeurArgent(),
                        new HashSet<>())));
    return fluxImpossibles;
  }

  @Override
  public Optional<FluxImpossibles> create(FluxImpossibles toCreate) {
    return Optional.empty();
  }

  @Override
  public Optional<FluxImpossibles> update(FluxImpossibles toCreate, FluxImpossibles toUpdated) {
    return Optional.empty();
  }
}
