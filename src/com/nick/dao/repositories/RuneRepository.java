package com.nick.dao.repositories;

import com.google.gson.reflect.TypeToken;
import com.nick.dao.entities.Rune;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RuneRepository extends AbstractRepository<Rune> {
    public Optional get(UUID id) {
        return Optional.empty();
    }


    public Optional get(String name) {
        return Optional.empty();
    }


    public List getAll() {
        return null;
    }


    public void add(Object o) {

    }

    public void update(Object o) {

    }

    public void delete(Object o) {

    }

    @Override
    public String getSaveFileName() {
        return "runes";
    }

    @Override
    Type getRepositoryEntityListType() {
        return new TypeToken<List<Rune>>(){}.getType();
    }
}
