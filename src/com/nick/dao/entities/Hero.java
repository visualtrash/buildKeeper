package com.nick.dao.entities;

public class Hero extends BKEntity {
    private String name;
    private String position;

    @Override
    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
