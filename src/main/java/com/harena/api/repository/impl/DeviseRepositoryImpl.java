package com.harena.api.repository.impl;

import com.harena.api.dto.DeviseDTO;
import com.harena.api.repository.DeviseRepository;
import com.harena.api.repository.model.Devise;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DeviseRepositoryImpl extends BaseRepository<DeviseDTO> implements DeviseRepository {

    public DeviseRepositoryImpl(
            ReadDataFromJsonFile<DeviseDTO> readDataFromJsonFile,
            WriteDataToJsonFile<DeviseDTO> writeDataToJsonFile,
            @Value("${path.to.file.devise}") String filePath
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }

    @Override
    public List<Devise> loadAllData() {
        return super.loadAllData(DeviseDTO.class)
                .stream()
                .map(this::toDevise)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Devise> create(Devise toCreate) {
        var deviseDTO = toDeviseDTO(toCreate);
        return super.create(DeviseDTO.class, deviseDTO).map(this::toDevise);
    }

    @Override
    public Optional<Devise> update(Devise toCreate, Devise toUpdate) {
        var createDTO = toDeviseDTO(toCreate);
        var updateDTO = toDeviseDTO(toUpdate);
        return super.update(DeviseDTO.class, createDTO, updateDTO).map(this::toDevise);
    }

    @Override
    public Devise findDeviseByCode(String deviseCode) {
        return this.loadAllData().stream()
                .filter(devise -> devise.code().equals(deviseCode))
                .findFirst()
                .orElse(null);
    }

    private Devise toDevise(DeviseDTO deviseDTO) {
        return new Devise(deviseDTO.getNom(), deviseDTO.getCode());
    }

    private DeviseDTO toDeviseDTO(Devise devise) {
        return new DeviseDTO(devise.code(), devise.nom());
    }
}
