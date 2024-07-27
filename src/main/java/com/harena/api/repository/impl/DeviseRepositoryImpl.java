package com.harena.api.repository.impl;

import com.harena.api.dto.json.DeviseDataJsonFile;
import com.harena.api.repository.DeviseRepository;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

public class DeviseRepositoryImpl extends BaseRepository<DeviseDataJsonFile> implements DeviseRepository {
    public DeviseRepositoryImpl(
            ReadDataFromJsonFile<DeviseDataJsonFile> readDataFromJsonFile,
            WriteDataToJsonFile<DeviseDataJsonFile> writeDataToJsonFile,
            @Value("${path.to.file.devise}") String filePath
    ) {
        super(readDataFromJsonFile, writeDataToJsonFile, filePath);
    }

    @Override
    public List<DeviseDataJsonFile> loadAllData() {
        return super.loadAllData(DeviseDataJsonFile.class);
    }

    @Override
    public Optional<DeviseDataJsonFile> create(DeviseDataJsonFile toCreate) {
        return super.create(DeviseDataJsonFile.class, toCreate);
    }

    @Override
    public Optional<DeviseDataJsonFile> update(DeviseDataJsonFile toUpdate) {
        return super.update(DeviseDataJsonFile.class, toUpdate);
    }
}
