package com.nick.dao.services;

import com.nick.dao.entities.Item;

import java.util.List;
import java.util.UUID;

public class ItemService {
    private List<Item> itemList;

    public ItemService(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Item add(String name) {
        Item item = new Item(name);

        itemList.add(item);

        return item;
    }

    public void remove(UUID id) throws Exception {
        // флаг, обозначающий был ли найден предмет в списке
        boolean itemWasFounded = false;

        for (Item item : itemList) {
            if (item.getId().equals(id)) {
                itemWasFounded = true;

                itemList.remove(item);
                break;
            }
        }

        if (!itemWasFounded) {
            throw new Exception("Cannot find the item for ID = " + id);
        }
    }
}
