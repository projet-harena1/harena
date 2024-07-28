package com.harena.api.repository.impl;

import com.harena.api.dto.FluxArgentDTO;
import com.harena.api.repository.ArgentRepository;
import com.harena.api.repository.DeviseRepository;
import com.harena.api.repository.FluxArgentRepository;
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

    private final DeviseRepository deviseRepository;
    private final ArgentRepository argentRepository;

    protected FluxArgentRepositoryImpl(
            ReadDataFromJsonFile<FluxArgentDTO> readDataFromJsonFile,
            WriteDataToJsonFile<FluxArgentDTO> writeDataToJsonFile,
            @Value("${path.to.file.flux.argent}") String filePath,
            DeviseRepository deviseRepository,
            ArgentRepository argentRepository
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
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
        return FluxArgentDTO.builder()
                .argentNom(argentRepository.findArgentByNomAndPatrimoine(fluxArgent.getNom(), fluxArgent.getPatrimoine().nom()).getNom())
                .fin(fluxArgent.getFin())
                .patrimoineNom(fluxArgent.getPatrimoine().nom())
                .valeurComptable(fluxArgent.getValeurComptable())
                .debut(fluxArgent.getDebut())
                .dateOperation(fluxArgent.getDateOperation())
                .nom(fluxArgent.getNom())
                .fluxImpossiblesDate(null)
                .fluxMensuel(fluxArgent.getFluxMensuel())
                .t(fluxArgent.getT())
                .deviseCode(fluxArgent.getDevise().code())
                .build();
    }

    private FluxArgent toFluxArgent(FluxArgentDTO dto) {
        return new FluxArgent(
                dto.getNom(),
                argentRepository.findArgentByNomAndPatrimoine(dto.getArgentNom(), dto.getPatrimoineNom()),
                dto.getDebut(),
                dto.getFin(),
                dto.getFluxMensuel(),
                dto.getDateOperation(),
                deviseRepository.findDeviseByCode(dto.getDeviseCode())
        );
    }

}
