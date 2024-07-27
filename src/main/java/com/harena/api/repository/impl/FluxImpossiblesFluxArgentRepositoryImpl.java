package com.harena.api.repository.impl;

import com.harena.api.dto.json.FluxImpossiblesFluxArgentsDataJsonFile;
import com.harena.api.repository.FluxImpossiblesFluxArgentRepository;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FluxImpossiblesFluxArgentRepositoryImpl extends BaseRepository<FluxImpossiblesFluxArgentsDataJsonFile> implements FluxImpossiblesFluxArgentRepository {

    public FluxImpossiblesFluxArgentRepositoryImpl(ReadDataFromJsonFile<FluxImpossiblesFluxArgentsDataJsonFile> readDataFromJsonFile, WriteDataToJsonFile<FluxImpossiblesFluxArgentsDataJsonFile> writeDataToJsonFile,@Value("${path.to.file.flux.impossibles.flux.argents}") String filePath) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }

    @Override
    public List<FluxImpossiblesFluxArgentsDataJsonFile> loadAllData() {
        return super.loadAllData(FluxImpossiblesFluxArgentsDataJsonFile.class);
    }

    @Override
    public Optional<FluxImpossiblesFluxArgentsDataJsonFile> create(FluxImpossiblesFluxArgentsDataJsonFile toCreate) {
        return super.create(FluxImpossiblesFluxArgentsDataJsonFile.class, toCreate);
    }

    @Override
    public Optional<FluxImpossiblesFluxArgentsDataJsonFile> update(FluxImpossiblesFluxArgentsDataJsonFile toUpdated) {
        return super.update(FluxImpossiblesFluxArgentsDataJsonFile.class, toUpdated);
    }
}
