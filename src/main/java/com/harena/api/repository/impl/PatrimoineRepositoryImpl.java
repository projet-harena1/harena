package com.harena.api.repository.impl;

import com.harena.api.dto.PatrimoineDTO;
import com.harena.api.repository.PatrimoineRepository;
import com.harena.api.repository.PersonRepository;
import com.harena.api.repository.model.Patrimoine;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class PatrimoineRepositoryImpl extends BaseRepository<PatrimoineDTO> implements PatrimoineRepository {
    private final PersonRepository personRepository;

    public PatrimoineRepositoryImpl(
            ReadDataFromJsonFile<PatrimoineDTO> readDataFromJsonFile,
            WriteDataToJsonFile<PatrimoineDTO> writeDataToJsonFile,
            @Value("${path.to.file.patrimoine}") String filePath,
            PersonRepository personRepository
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
        this.personRepository = personRepository;
    }


    @Override
    public List<Patrimoine> loadAllData() {
        return List.of();
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
                new HashSet<>()
        );
    }

    @Override
    public Patrimoine findPatrimoineByNom(String nom) {
        return this.loadAllData().stream()
                .filter(patrimoine -> patrimoine.nom().equals(nom))
                .findFirst().orElse(null);
    }
}
