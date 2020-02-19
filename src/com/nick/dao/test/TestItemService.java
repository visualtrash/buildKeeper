package com.nick.dao.test;

import com.nick.dao.entities.Item;
import com.nick.dao.repositories.ItemRepository;
import com.nick.dao.services.ItemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TestItemService {
    private ItemService itemService = new ItemService(new ItemRepository());
    private ItemRepository itemRepository = new ItemRepository();
    private UUID id = getRandomIdItem();

    @Before
    private UUID getRandomIdItem() {
        List<Item> itemList = itemRepository.getAll();
        return itemList.get(0).getId();
    }

    @Test
    public void testAdd() {
        try {
            itemService.add("itemName");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void removeById() {
        try {
            itemRepository.deleteById(id);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public Optional getItemById() {
        return itemRepository.get(id);
    }


}
