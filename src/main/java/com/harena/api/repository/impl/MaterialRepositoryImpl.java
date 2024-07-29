package com.harena.api.repository.impl;

import com.harena.api.dto.DeviseDTO;
import com.harena.api.dto.MaterielDTO;
import com.harena.api.exceptions.InternalServerException;
import com.harena.api.repository.DeviseRepository;
import com.harena.api.repository.MaterielRepository;
import com.harena.api.repository.PatrimoineRepository;
import com.harena.api.repository.model.Devise;
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
    private final PatrimoineRepository patrimoineRepository;
    public MaterialRepositoryImpl(
            ReadDataFromJsonFile<MaterielDTO> readDataFromJsonFile,
            WriteDataToJsonFile<MaterielDTO> writeDataToJsonFile,
            @Value("${path.to.file.materiel}") String filePath,
            DeviseRepository deviseRepository, PatrimoineRepository patrimoineRepository
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
        this.deviseRepository = deviseRepository;
        this.patrimoineRepository = patrimoineRepository;
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
        var devise = (materiel.getDevise() != null) ? new DeviseDTO(materiel.getDevise().nom(), materiel.getDevise().code()) : null;
        return new MaterielDTO(
                materiel.getNom(),
                materiel.getT(),
                materiel.getPatrimoine().nom(),
                materiel.getValeurComptable(),
                devise,
                materiel.getDateAcquisition(),
                materiel.getTauxDAppreciationAnnuelle()
        );
    }


    private Materiel toMateriel(MaterielDTO materielDTO) {
        var patrimoine = (materielDTO.getPatrimoineNom() != null)
                ? patrimoineRepository.findPatrimoineByNom(materielDTO.getPatrimoineNom())
                : null;

        String code = materielDTO.getDevise().getCode();
        Devise devise = deviseRepository.findDeviseByCode(code);
        if (devise == null) {
            var createdDevise = deviseRepository.create(new Devise(materielDTO.getDevise().getNom(), code));
            if (createdDevise.isPresent()) {
                devise = createdDevise.get();
            } else {
                throw new InternalServerException("Failed to create Devise with code: " + code);
            }
        }
        return new Materiel(
                materielDTO.getNom(),
                materielDTO.getT(),
                materielDTO.getValeurComptable(),
                devise,
                patrimoine,
                materielDTO.getDateAcquisition(),
                materielDTO.getTauxDAppreciationAnnuelle()
        );
    }


    @Override
    public Materiel findMaterielByNom(String nom) {
        return this.loadAllData().stream().filter(materiel -> materiel.getNom().equals(nom))
                .findFirst().orElse(null);
    }

    @Override
    public void delete(Materiel materiel) {
        super.delete(MaterielDTO.class, toMaterielDTO(materiel));
    }
}
