package com.nick.dal.repositories;

import com.google.gson.reflect.TypeToken;
import com.nick.dal.entities.Item;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ItemRepository extends AbstractRepository<Item> {

    public Optional<Item> get(UUID id) {
        for (Item item : data) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public Optional get(String name) {
        for (Item item : data) {
            if (item.getName().equals(name)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    //add item in List
    public void add(Item item) throws Exception {
        data.add(item);
        persist();
        System.out.println("successful added");
    }

    public void update(Item itemToUpdate) throws Exception {
        Optional<Item> optionalItem = get(itemToUpdate.getId());

        if (optionalItem.isPresent()) {
            Item item1 = optionalItem.get();
            item1.setName(itemToUpdate.getName());
            persist();
        } else System.out.println("item not found(null)");
    }

    @Override
    public String getSaveFileName() {
        return "items";
    }

    @Override
    Type getRepositoryEntityListType() {
        return new TypeToken<List<Item>>(){}.getType();
    }
}
