package com.northwind.backend.service;

import java.util.List;
import java.util.Optional;

public interface BaseService {
    public <T> Optional<T> createEntity(T entity);

    public <T> List<T> findAllEntity();

    public <T> T findEntityById(Long id);

    public void deleteEntityById(Long id);

}
