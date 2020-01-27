package com.nick.dao.services;

import com.nick.dao.entities.Build;
import com.nick.dao.entities.Hero;
import com.nick.dao.entities.Item;
import com.nick.dao.entities.Rune;
import com.nick.dao.repositories.BuildRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BuildService {

    private BuildRepository buildRepository;

    public BuildService(BuildRepository buildRepository) {
        this.buildRepository = buildRepository;
    }


    public void save(Build build) {
        buildRepository.add(build);
    }

    public void remove(Build build) throws Exception {
        buildRepository.delete(build);
    }


    public void updateName(UUID id, String name) throws Exception {
        Optional<Build> optionalBuild = buildRepository.get(id);
        if (optionalBuild.isPresent()) {
            Build build1 = optionalBuild.get();
            build1.setName(name);
            buildRepository.update(build1);
        }
    }

    public void updateRunes(UUID id, Rune rune) throws Exception {
        Optional<Build> optionalBuild = buildRepository.get(id);
        if (optionalBuild.isPresent()) {
            Build build1 = optionalBuild.get();
            build1.setRune(rune);
            buildRepository.update(build1);
        }
    }

    public void updateHero(UUID id, Hero hero) throws Exception {
        Optional<Build> optionalBuild = buildRepository.get(id);
        if (optionalBuild.isPresent()) {
            Build build1 = optionalBuild.get();
            build1.setHero(hero);
            buildRepository.update(build1);
        }
    }

    public void updateItems(UUID id, List<Item> items) throws Exception {
        Optional<Build> optionalBuild = buildRepository.get(id);
        if (optionalBuild.isPresent()) {
            Build build1 = optionalBuild.get();
            build1.setItems(items);
            buildRepository.update(build1);
        }
    }
}
