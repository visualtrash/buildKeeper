package com.nick.dao.services;

import com.nick.dao.entities.Build;
import com.nick.dao.entities.Hero;
import com.nick.dao.entities.Item;
import com.nick.dao.entities.Rune;
import com.nick.enums.Ability;

import java.util.List;
import java.util.UUID;

public class BuildService {

    private List<Build> buildList;

    public BuildService(List<Build> buildList) {
        this.buildList = buildList;
    }

    public Build add(String name, Hero hero, List<Item> items, Rune rune, Ability ability) {
        Build build = new Build(name, hero, items, rune, ability);

        buildList.add(build);

        return build;
    }

    public void remove(UUID id) throws Exception {
        // флаг, обозначающий был ли найден билд в списке
        boolean buildWasFounded = false;

        for (Build build : buildList) {
            if (build.getId().equals(id)) {
                buildWasFounded = true;

                buildList.remove(build);
                break;
            }
        }

        if (!buildWasFounded) {
            throw new Exception("Cannot find the build for ID = " + id);
        }
    }

    public void rename(UUID id, String name) throws Exception {
        // флаг, обозначающий был ли найден билд в списке
        boolean buildWasFounded = false;

        for (Build build : buildList) {
            if (build.getId().equals(id)) {
                buildWasFounded = true;

                build.setName(name);

                break;
            }
        }

        if (!buildWasFounded) {
            throw new Exception("Cannot find build for ID = " + id);
        }
    }

    public void editItems(UUID id, List<Item> itemList) throws Exception {
        // флаг, обозначающий был ли найден билд в списке
        boolean buildWasFounded = false;

        for (Build build : buildList) {
            if (build.getId().equals(id)) {
                buildWasFounded = true;

                build.setItems(itemList);

                break;
            }
        }

        if (!buildWasFounded) {
            throw new Exception("Cannot find build for ID = " + id);
        }
    }

    // TODO: после класс Rune
    public void editRunes(UUID id) throws Exception {

    }

    public void editHero(UUID id, Hero hero) throws Exception {
        // флаг, обозначающий был ли найден билд в списке
        boolean buildWasFounded = false;

        for (Build build : buildList) {
            if (build.getId().equals(id)) {
                buildWasFounded = true;

                build.setHero(hero);

                break;
            }
        }

        if (!buildWasFounded) {
            throw new Exception("Cannot find build for ID = " + id);
        }
    }
}
