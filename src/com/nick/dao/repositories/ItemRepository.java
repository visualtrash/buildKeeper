package com.nick.dao.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ItemRepository implements BKRepository {


    @Override
    public Optional get(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional get(String name) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }
}
