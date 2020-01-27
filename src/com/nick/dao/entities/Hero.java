package com.nick.dao.entities;

public class Hero extends BKEntity {
    private String name;
    private String position;

    public Hero(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
