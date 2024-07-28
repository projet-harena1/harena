package com.harena.api.repository.impl;

import com.harena.api.dto.MaterielDTO;
import com.harena.api.repository.DeviseRepository;
import com.harena.api.repository.MaterielRepository;
import com.harena.api.repository.model.possession.Materiel;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MaterialRepositoryImpl extends BaseRepository<MaterielDTO> implements MaterielRepository {
    private final DeviseRepository deviseRepository;

    public MaterialRepositoryImpl(
            ReadDataFromJsonFile<MaterielDTO> readDataFromJsonFile,
            WriteDataToJsonFile<MaterielDTO> writeDataToJsonFile,
            @Value("${path.to.file.materiel}") String filePath,
            DeviseRepository deviseRepository
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
        this.deviseRepository = deviseRepository;
    }

    @Override
    public List<Materiel> loadAllData() {
        var materiels = new ArrayList<Materiel>();
        super.loadAllData(MaterielDTO.class).forEach(materielDTO -> materiels.add(this.toMateriel(materielDTO)));
        return materiels;
    }


    @Override
    public Optional<Materiel> create(Materiel toCreate) {
        var dto = toMaterielDTO(toCreate);
        return super.create(MaterielDTO.class, dto).map(this::toMateriel);
    }

    @Override
    public Optional<Materiel> update(Materiel toCreate, Materiel toUpdate) {
        var createDTO = toMaterielDTO(toCreate);
        var updateDTO = toMaterielDTO(toUpdate);
        return super.update(MaterielDTO.class, createDTO, updateDTO).map(this::toMateriel);
    }

    private MaterielDTO toMaterielDTO(Materiel materiel) {
        if (materiel == null) {
            return null;
        }
        return new MaterielDTO(
                materiel.getNom(),
                materiel.getT(),
                materiel.getPatrimoine().nom(),
                materiel.getValeurComptable(),
                materiel.getDevise().code(),
                materiel.getDateAcquisition(),
                materiel.getTauxDAppreciationAnnuelle()
        );
    }

    private Materiel toMateriel(MaterielDTO materielDTO) {
        return new Materiel(
                materielDTO.getNom(),
                materielDTO.getT(),
                materielDTO.getValeurComptable(),
                materielDTO.getDateAcquisition(),
                materielDTO.getTauxDAppreciationAnnuelle(),
                deviseRepository.findDeviseByCode(materielDTO.getDeviseCode())
        );
    }

    @Override
    public Materiel findMaterielByNom(String nom) {
        return this.loadAllData().stream().filter(materiel -> materiel.getNom().equals(nom))
                .findFirst().orElse(null);
    }
}
