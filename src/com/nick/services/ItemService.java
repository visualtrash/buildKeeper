package com.nick.services;

import com.nick.dal.entities.Item;
import com.nick.dal.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void add(String itemName) throws Exception {
        itemRepository.add(new Item(itemName));
    }

    public List getItemList() {
        return itemRepository.getAll();
    }

    public Optional getItemById(UUID itemId) throws Exception {
        return itemRepository.get(itemId);
    }

    public void removeById(UUID itemId) throws Exception {
        itemRepository.deleteById(itemId);
    }
}