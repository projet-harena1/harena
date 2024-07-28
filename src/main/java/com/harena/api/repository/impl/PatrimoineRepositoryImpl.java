package com.harena.api.repository.impl;

import com.harena.api.dto.PatrimoineDTO;
import com.harena.api.repository.*;
import com.harena.api.repository.model.Patrimoine;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class PatrimoineRepositoryImpl extends BaseRepository<PatrimoineDTO> implements PatrimoineRepository {
    private final PersonRepository personRepository;
    private final MaterielRepository materielRepository;
    private final FluxArgentRepository fluxArgentRepository;
    private final ArgentRepository argentRepository;

    public PatrimoineRepositoryImpl(ReadDataFromJsonFile<PatrimoineDTO> readDataFromJsonFile, WriteDataToJsonFile<PatrimoineDTO> writeDataToJsonFile, @Value("${path.to.file.patrimoine}") String filePath, PersonRepository personRepository, MaterielRepository materielRepository, FluxArgentRepository fluxArgentRepository, ArgentRepository argentRepository) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
        this.personRepository = personRepository;
        this.materielRepository = materielRepository;
        this.fluxArgentRepository = fluxArgentRepository;
        this.argentRepository = argentRepository;
    }

    @Override
    public List<Patrimoine> loadAllData() {
        return super.loadAllData(PatrimoineDTO.class).stream()
                .map(this::toPatrimoine)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Patrimoine> create(Patrimoine toCreate) {
        var dto = toPatrimoineDTO(toCreate);
        return super.create(PatrimoineDTO.class, dto).map(this::toPatrimoine);
    }

    @Override
    public Optional<Patrimoine> update(Patrimoine toCreate, Patrimoine toUpdate) {
        var createDTO = toPatrimoineDTO(toCreate);
        var updateDTO = toPatrimoineDTO(toUpdate);
        return super.update(PatrimoineDTO.class, createDTO, updateDTO).map(this::toPatrimoine);
    }

    private PatrimoineDTO toPatrimoineDTO(Patrimoine patrimoine) {
        return PatrimoineDTO.builder()
                .nom(patrimoine.nom())
                .personNom(patrimoine.possesseur() != null ? patrimoine.possesseur().nom() : null)
                .t(patrimoine.t())
                .build();
    }

    private Patrimoine toPatrimoine(PatrimoineDTO patrimoineDTO) {
        var possesseur = personRepository.findPersonByNom(patrimoineDTO.getPersonNom());
        return new Patrimoine(
                patrimoineDTO.getNom(),
                possesseur,
                patrimoineDTO.getT(),
                Stream.concat(
                        fluxArgentRepository.loadAllData().stream()
                                .filter(fluxArgent -> fluxArgent.getPatrimoine() != null && fluxArgent.getPatrimoine().nom().equals(patrimoineDTO.getNom())),
                        Stream.concat(
                                materielRepository.loadAllData().stream()
                                        .filter(materiel -> materiel.getPatrimoine() != null && materiel.getPatrimoine().nom().equals(patrimoineDTO.getNom())),
                                argentRepository.loadAllData().stream()
                                        .filter(argent -> argent.getPatrimoine() != null && argent.getPatrimoine().nom().equals(patrimoineDTO.getNom()))
                        )
                ).collect(Collectors.toSet())
        );
    }

    @Override
    public Patrimoine findPatrimoineByNom(String nom) {
        return this.loadAllData().stream()
                .filter(patrimoine -> patrimoine.nom().equals(nom))
                .findFirst().orElse(null);
    }
}
