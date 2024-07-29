package com.harena.api.repository.impl;

import com.harena.api.dto.ArgentDTO;
import com.harena.api.dto.DeviseDTO;
import com.harena.api.enums.ArgentType;
import com.harena.api.repository.ArgentRepository;
import com.harena.api.repository.DeviseRepository;
import com.harena.api.repository.PatrimoineRepository;
import com.harena.api.repository.model.Devise;
import com.harena.api.repository.model.possession.Argent;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class ArgentRepositoryImpl extends BaseRepository<ArgentDTO> implements ArgentRepository {
    private final DeviseRepository deviseRepository;
    private final PatrimoineRepository patrimoineRepository;

    public ArgentRepositoryImpl(
            ReadDataFromJsonFile<ArgentDTO> readDataFromJsonFile,
            WriteDataToJsonFile<ArgentDTO> writeDataToJsonFile,
            @Value("${path.to.file.argent}") String filePath,
            DeviseRepository deviseRepository, PatrimoineRepository patrimoineRepository
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
        this.deviseRepository = deviseRepository;
        this.patrimoineRepository = patrimoineRepository;
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

    @Override
    public Argent findArgentByNom(String nom) {
        return this.loadAllData().stream().filter(argent -> argent.getNom().equals(nom))
                .findFirst().orElse(null);
    }

    @Override
    public void delete(Argent argent) {
        super.delete(ArgentDTO.class, toArgentDTO(argent));
    }

    private ArgentDTO toArgentDTO(Argent argent) {
        var type = determineArgentType(argent);
        var patrimoineNom = (argent.getPatrimoine() != null) ? argent.getPatrimoine().nom() : null;
        var devise = argent.getDevise();
        var deviseDTO = findOrCreateDeviseDTO(devise);
        return new ArgentDTO(
                argent.getNom(),
                argent.getT(),
                patrimoineNom,
                argent.getValeurComptable(),
                deviseDTO,
                argent.getDateOuverture(),
                type
        );
    }

    private DeviseDTO findOrCreateDeviseDTO(Devise devise) {
        var foundDevise = deviseRepository.findDeviseByCode(devise.code());
        if (foundDevise == null) {
            var createdDevise = deviseRepository.create(new Devise(devise.nom(), devise.code()))
                    .orElseThrow(() -> new IllegalStateException("Failed to create Devise with code: " + devise.code()));
            return new DeviseDTO(createdDevise.nom(), createdDevise.code());
        } else {
            return new DeviseDTO(foundDevise.nom(), foundDevise.code());
        }
    }

    private Argent toArgent(ArgentDTO argentDTO) {
        var patrimoine = (argentDTO.getPatrimoineNom() != null)
                ? patrimoineRepository.findPatrimoineByNom(argentDTO.getPatrimoineNom())
                : null;

        var deviseDTO = argentDTO.getDevise();
        var devise = deviseDTO != null ? new Devise(
                deviseDTO.getNom(),
                deviseDTO.getCode()
        ) : null;

        return new Argent(
                argentDTO.getNom(),
                argentDTO.getT(),
                argentDTO.getValeurComptable(),
                devise,
                patrimoine,
                argentDTO.getDateOuverture(),
                new HashSet<>()
        );
    }

    private ArgentType determineArgentType(Argent argent) {
        if (argent.getValeurComptable() > 0) {
            return ArgentType.CREANCE;
        } else if (argent.getValeurComptable() < 0) {
            return ArgentType.DETTE;
        } else {
            return ArgentType.AUTRES;
        }
    }
}
