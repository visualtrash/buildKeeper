package com.nick.dao.entities;

public class Hero extends BKEntity {
    private String position;

    public Hero(String name, String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
