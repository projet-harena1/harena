package com.harena.api.repository;

import java.util.List;
import java.util.Optional;

public interface LoadDataFromJsonFileInterface<T> {
    List<T> loadAllData();

    Optional<T> create(T toCreate);

    Optional<T> update(T toUpdated);
}
