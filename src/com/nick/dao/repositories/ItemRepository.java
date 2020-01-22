package com.nick.dao.repositories;

import com.nick.dao.entities.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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
        for (Item itemOfList : itemList) {
            System.out.println(itemOfList.getName());
        }
        return itemList;
    }

    //add item in List
    public void save(Item item) {
        itemList.add(item);
    }

    //save item on disk
    @SuppressWarnings("Duplicates")
    public void update(Item item) {
        StringBuilder result = new StringBuilder();

        for (Item currentItem : itemList) {
            String itemLine = currentItem.getName();
            result.append(itemLine).append("\n");
        }

        File file = new File("C://BKrep", "ItemRep.txt");

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(result.toString());
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Item item) throws Exception {
        // флаг, был ли найден hero в списке
        boolean heroWasFounded = false;

        for (Item currentHero : itemList)
            if (currentHero.getId().equals(item.getId())) {
                heroWasFounded = true;

                itemList.remove(currentHero);
                break;
            }
        if (!heroWasFounded) {
            throw new Exception("Cannot find the item");
        }
    }
}
