package com.nick.dao.entities;

import java.util.UUID;

abstract class BKEntity {
    private UUID id;
    private String name;

    BKEntity() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
