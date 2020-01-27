package com.nick.dao.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BKRepository<T> {
    Optional<T> get(UUID id) throws Exception;

    Optional<T> get(String name);

    List<T> getAll();

    void add(T t) throws Exception;

    void update(T t) throws Exception;

    void delete(T t) throws Exception;
}