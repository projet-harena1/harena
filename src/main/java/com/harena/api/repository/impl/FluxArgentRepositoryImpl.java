package com.harena.api.repository.impl;

import com.harena.api.dto.DeviseDTO;
import com.harena.api.dto.FluxArgentDTO;
import com.harena.api.exceptions.InternalServerException;
import com.harena.api.repository.ArgentRepository;
import com.harena.api.repository.DeviseRepository;
import com.harena.api.repository.FluxArgentRepository;
import com.harena.api.repository.PatrimoineRepository;
import com.harena.api.repository.model.Devise;
import com.harena.api.repository.model.possession.FluxArgent;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FluxArgentRepositoryImpl extends BaseRepository<FluxArgentDTO> implements FluxArgentRepository {
    private final PatrimoineRepository patrimoineRepository;
    private final DeviseRepository deviseRepository;
    private final ArgentRepository argentRepository;

    protected FluxArgentRepositoryImpl(
            ReadDataFromJsonFile<FluxArgentDTO> readDataFromJsonFile,
            WriteDataToJsonFile<FluxArgentDTO> writeDataToJsonFile,
            @Value("${path.to.file.flux.argent}") String filePath, PatrimoineRepository patrimoineRepository,
            DeviseRepository deviseRepository,
            ArgentRepository argentRepository
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
        this.patrimoineRepository = patrimoineRepository;
        this.deviseRepository = deviseRepository;
        this.argentRepository = argentRepository;
    }

    @Override
    public List<FluxArgent> loadAllData() {
        var flux = new ArrayList<FluxArgent>();
        super.loadAllData(FluxArgentDTO.class).forEach(fluxArgentDTO -> flux.add(this.toFluxArgent(fluxArgentDTO)));
        return flux;
    }

    @Override
    public Optional<FluxArgent> create(FluxArgent toCreate) {
        var dto = toFluxArgentDTO(toCreate);
        return super.create(FluxArgentDTO.class, dto).map(this::toFluxArgent);
    }

    @Override
    public Optional<FluxArgent> update(FluxArgent toCreate, FluxArgent toUpdate) {
        var createDTO = toFluxArgentDTO(toCreate);
        var updateDTO = toFluxArgentDTO(toUpdate);
        return super.update(FluxArgentDTO.class, createDTO, updateDTO).map(this::toFluxArgent);
    }

    private FluxArgentDTO toFluxArgentDTO(FluxArgent fluxArgent) {
        var argentNom = argentRepository.findArgentByNomAndPatrimoine(fluxArgent.getNom(), fluxArgent.getPatrimoine().nom());
        var devise = fluxArgent.getDevise();
        var foundDevise = deviseRepository.findDeviseByCode(devise.code());
        if (foundDevise == null) {
            devise = deviseRepository.create(new Devise(devise.nom(), devise.code())).orElseThrow(
                    () -> new InternalServerException("Error create devise"));
        } else {
            devise = foundDevise;
        }
        return FluxArgentDTO.builder()
                .argentNom(argentNom.getNom())
                .fin(fluxArgent.getFin())
                .patrimoineNom(fluxArgent.getPatrimoine().nom())
                .valeurComptable(fluxArgent.getValeurComptable())
                .debut(fluxArgent.getDebut())
                .dateOperation(fluxArgent.getDateOperation())
                .nom(fluxArgent.getNom())
                .fluxImpossiblesDate(null)
                .fluxMensuel(fluxArgent.getFluxMensuel())
                .t(fluxArgent.getT())
                .devise(new DeviseDTO(devise.nom(), devise.code()))
                .build();
    }



    private FluxArgent toFluxArgent(FluxArgentDTO dto) {
        var patrimoine = (dto.getPatrimoineNom() != null)
                ? patrimoineRepository.findPatrimoineByNom(dto.getPatrimoineNom())
                : null;
        var deviseDTO = dto.getDevise();
        var devise = deviseDTO != null ? new Devise(
                deviseDTO.getNom(),
                deviseDTO.getCode()
        ): null;
        return new FluxArgent(
                dto.getNom(),
                dto.getT(),
                dto.getValeurComptable(),
                devise,
                patrimoine,
                argentRepository.findArgentByNomAndPatrimoine(dto.getArgentNom(), dto.getPatrimoineNom()),
                dto.getDebut(),
                dto.getFin(),
                dto.getFluxMensuel(),
                dto.getDateOperation()
        );
    }

    @Override
    public FluxArgent findFluxArgentByNom(String nom) {
        return this.loadAllData().stream().filter(fluxArgent -> fluxArgent.getNom().equals(nom))
                .findFirst().orElse(null);
    }

    @Override
    public void delete(FluxArgent fluxArgent) {
        super.delete(FluxArgentDTO.class, toFluxArgentDTO(fluxArgent));
    }
}
