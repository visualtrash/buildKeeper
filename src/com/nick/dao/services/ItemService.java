package com.nick.dao.services;

import com.nick.dao.entities.Item;
import com.nick.dao.repositories.ItemRepository;

public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void save(Item item) {
        itemRepository.add(item);
    }

    public void remove(Item hero) throws Exception {
        itemRepository.delete(hero);
    }
}
