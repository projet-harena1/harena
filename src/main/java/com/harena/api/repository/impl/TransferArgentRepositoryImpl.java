package com.harena.api.repository.impl;

import com.harena.api.dto.json.TransfertArgentDataJsonFile;
import com.harena.api.repository.TransferArgentRepository;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransferArgentRepositoryImpl extends BaseRepository<TransfertArgentDataJsonFile> implements TransferArgentRepository {
    public TransferArgentRepositoryImpl(ReadDataFromJsonFile<TransfertArgentDataJsonFile> readDataFromJsonFile, WriteDataToJsonFile<TransfertArgentDataJsonFile> writeDataToJsonFile, @Value("${path.to.file.transfert.argent}") String filePath) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }

    @Override
    public List<TransfertArgentDataJsonFile> loadAllData() {
        return super.loadAllData(TransfertArgentDataJsonFile.class);
    }

    @Override
    public Optional<TransfertArgentDataJsonFile> create(TransfertArgentDataJsonFile toCreate) {
        return super.create(TransfertArgentDataJsonFile.class, toCreate);
    }

    @Override
    public Optional<TransfertArgentDataJsonFile> update(TransfertArgentDataJsonFile toUpdated) {
        return super.update(TransfertArgentDataJsonFile.class, toUpdated);
    }
}
