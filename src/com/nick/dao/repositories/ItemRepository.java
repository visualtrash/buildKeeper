package com.nick.dao.repositories;

import com.nick.dao.entities.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ItemRepository implements BKRepository<Item> {
    private List<Item> itemList;

    public Optional<Item> get(UUID id) throws Exception {
        for (Item item : itemList) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public Optional get(String name) {
        for (Item item : itemList) {
            if (item.getName().equals(name)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public List getAll() {
        return itemList;
    }

    //add item in List
    public void add(Item item) throws Exception {
        itemList.add(item);
        update(item);
    }

    public void update(Item itemToUpdate) throws Exception {
        Optional<Item> optionalItem = get(itemToUpdate.getId());

        if (optionalItem.isPresent()) {
            Item item1 = optionalItem.get();
            item1.setName(itemToUpdate.getName());

        } else System.out.println("item not found(null)");

    }

    public void delete(Item item) throws Exception {
        // флаг, был ли найден hero в списке
        boolean heroWasFounded = false;

        for (Item currentHero : itemList)
            if (currentHero.getId().equals(item.getId())) {
                heroWasFounded = true;

                itemList.remove(currentHero);
                update(item);
                break;
            }
        if (!heroWasFounded) {
            throw new Exception("Cannot find the item");
        }
    }
}
