package com.harena.api.repository.impl;

import com.harena.api.dto.ArgentDTO;
import com.harena.api.enums.ArgentType;
import com.harena.api.repository.ArgentRepository;
import com.harena.api.repository.DeviseRepository;
import com.harena.api.repository.model.possession.Argent;
import com.harena.api.repository.model.possession.Creance;
import com.harena.api.repository.model.possession.Dette;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArgentRepositoryImpl extends BaseRepository<ArgentDTO> implements ArgentRepository {

    private final DeviseRepository deviseRepository;

    public ArgentRepositoryImpl(
            ReadDataFromJsonFile<ArgentDTO> readDataFromJsonFile,
            WriteDataToJsonFile<ArgentDTO> writeDataToJsonFile,
            @Value("${path.to.file.argent}") String filePath,
            DeviseRepository deviseRepository
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
        this.deviseRepository = deviseRepository;
    }

    @Override
    public List<Argent> loadAllData() {
        var argents = new ArrayList<Argent>();
        super.loadAllData(ArgentDTO.class).forEach(argentDTO -> argents.add(toArgent(argentDTO)));
        return argents;
    }

    @Override
    public Optional<Argent> create(Argent toCreate) {
        var argentDTO = toArgentDTO(toCreate);
        return super.create(ArgentDTO.class, argentDTO).map(this::toArgent);
    }

    @Override
    public Optional<Argent> update(Argent toCreate, Argent toUpdate) {
        var createDTO = toArgentDTO(toCreate);
        var updateDTO = toArgentDTO(toUpdate);
        return super.update(ArgentDTO.class, createDTO, updateDTO).map(this::toArgent);
    }

    @Override
    public Argent findArgentByNomAndPatrimoine(String argentNom, String patrimoineNom) {
        return loadAllData().stream()
                .filter(argent -> argent.getNom().equals(argentNom) && argent.getPatrimoine().nom().equals(patrimoineNom))
                .findFirst()
                .orElse(null);
    }

    private ArgentDTO toArgentDTO(Argent argent) {
        var type = determineArgentType(argent);
        return new ArgentDTO(
                argent.getNom(),
                argent.getT(),
                argent.getPatrimoine().nom(),
                argent.getValeurComptable(),
                argent.getDevise().nom(),
                argent.getDateOuverture(),
                type
        );
    }

    private Argent toArgent(ArgentDTO argentDTO) {
        return new Argent(
                argentDTO.getNom(),
                argentDTO.getDateOuverture(),
                argentDTO.getT(),
                argentDTO.getValeurComptable(),
                deviseRepository.findDeviseByCode(argentDTO.getDeviseCode())
        );
    }

    private ArgentType determineArgentType(Argent argent) {
        if (argent instanceof Dette) {
            return ArgentType.DETTE;
        } else if (argent instanceof Creance) {
            return ArgentType.CREANCE;
        } else {
            return ArgentType.AUTRES;
        }
    }
}
