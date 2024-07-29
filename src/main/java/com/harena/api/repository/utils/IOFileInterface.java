package com.harena.api.repository.utils;


import java.util.List;
import java.util.Optional;

public interface IOFileInterface<T> {
    List<T> loadAllData();

    Optional<T> create(T toCreate);

    Optional<T> update(T toCreate, T toUpdated);
}
