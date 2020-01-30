package com.nick;

import com.nick.dao.entities.Build;
import com.nick.dao.entities.Hero;
import com.nick.dao.entities.Item;
import com.nick.dao.entities.Rune;
import com.nick.dao.repositories.BuildRepository;
import com.nick.enums.Ability;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        //Test
        Hero atrox = new Hero("Atrox", "top");

        List<Item> items1 = new ArrayList<>();
        Item item = new Item("boots");
        items1.add(item);

        Rune rune = new Rune();

        Build build = new Build("Atrox-top", atrox, items1, rune, Ability.Q);

        BuildRepository buildRepository = new BuildRepository();
        buildRepository.add(build);
        try {
            buildRepository.persist();
        } catch (Exception e) {
            e.printStackTrace();
        }

        buildRepository.load();
    }
}
