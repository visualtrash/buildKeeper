package com.nick.dao.services;

import com.nick.dao.entities.Item;
import com.nick.dao.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void add(Item item) throws Exception {
        itemRepository.add(item);
    }

    public void remove(Item item) throws Exception {
        itemRepository.delete(item);
    }

    public List getItemList() {
        return itemRepository.getAll();
    }

    public Optional getItemById(UUID itemId) throws Exception {
        return itemRepository.get(itemId);
    }

    public void removeById(UUID itemId) throws Exception {
        itemRepository.removeById(itemId);
    }
}