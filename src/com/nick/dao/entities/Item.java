package com.nick.dao.entities;

public class Item extends BKEntity {
    private String name;


    public Item(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
