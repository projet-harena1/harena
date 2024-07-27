package com.harena.api.repository.impl;

import com.harena.api.dto.json.MaterielDataJsonFile;
import com.harena.api.repository.MaterielRepository;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MaterielRepositoryImpl extends BaseRepository<MaterielDataJsonFile> implements MaterielRepository {

    public MaterielRepositoryImpl(
            ReadDataFromJsonFile<MaterielDataJsonFile> readDataFromJsonFile,
            WriteDataToJsonFile<MaterielDataJsonFile> writeDataToJsonFile,
            @Value("${path.to.file.materiel}") String filePath) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }

    @Override
    public List<MaterielDataJsonFile> loadAllData() {
        return super.loadAllData(MaterielDataJsonFile.class);
    }

    @Override
    public Optional<MaterielDataJsonFile> create(MaterielDataJsonFile toCreate) {
        return super.create(MaterielDataJsonFile.class, toCreate);
    }

    @Override
    public Optional<MaterielDataJsonFile> update(MaterielDataJsonFile toUpdated) {
        return super.update(MaterielDataJsonFile.class, toUpdated);
    }
}
