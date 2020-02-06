package com.nick.dao.repositories;

import com.nick.dao.entities.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ItemRepository extends AbstractRepository implements BKRepository<Item> {
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

        for (Item currentItem : itemList)
            if (currentItem.getId().equals(item.getId())) {
                heroWasFounded = true;

                itemList.remove(currentItem);
                update(item);
                break;
            }
        if (!heroWasFounded) {
            throw new Exception("Cannot find the item");
        }
    }

    public void removeById(UUID ItemId) throws Exception {
        // флаг, был ли найден hero в списке
        boolean heroWasFounded = false;

        for (Item currentItem : itemList)
            if (currentItem.getId().equals(ItemId)) {
                heroWasFounded = true;

                itemList.remove(currentItem);
                System.out.println("the ITEM was successfully REMOVED");
                break;
            }
        if (!heroWasFounded) {
            throw new Exception("Cannot find the item");
        }
    }

    public void removeByName(String nameItem) throws Exception {
        // флаг, был ли найден hero в списке
        boolean heroWasFounded = false;

        for (Item currentItem : itemList)
            if (currentItem.getName().equals(nameItem)) {
                heroWasFounded = true;

                itemList.remove(currentItem);
                System.out.println("the ITEM was successfully REMOVED");
                break;
            }
        if (!heroWasFounded) {
            throw new Exception("Cannot find the item");
        }
    }

    public static String getSaveFileName() {
        return "itemList";
    }
}
