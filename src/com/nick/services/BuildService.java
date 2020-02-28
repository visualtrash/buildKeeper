package com.nick.services;

import com.nick.dal.entities.Build;
import com.nick.dal.entities.Hero;
import com.nick.dal.entities.Item;
import com.nick.dal.entities.Rune;
import com.nick.dal.repositories.BuildRepository;
import com.nick.enums.Ability;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BuildService {

    private BuildRepository buildRepository;

    public BuildService(BuildRepository buildRepository) {
        this.buildRepository = buildRepository;
    }

    public List getBuildList() {
        return buildRepository.getAll();
    }

    public Build add(String userBuildName, Hero hero, List<Item> userBuildItems, Rune rune, List<Ability> abilities, String userHeroPosition) {
        Build newBuild = new Build(userBuildName, hero, userBuildItems, rune, abilities, userHeroPosition);
        buildRepository.add(newBuild);

        return newBuild;
    }

    public Build add(Build build) {
        buildRepository.add(build);
        return build;
    }

    public void removeById(UUID buildId) throws Exception {
        buildRepository.deleteById(buildId);
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

    public void editPosition(UUID id, String position) {
        Optional<Build> optionalBuild = buildRepository.get(id);

        if (optionalBuild.isPresent()) {
            Build build = optionalBuild.get();
            build.setHeroPosition(position);

            buildRepository.update(build);
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

    public void updateAbilities(UUID id, List<Ability> abilities) throws Exception {
        Optional<Build> optionalBuild = buildRepository.get(id);
        if (optionalBuild.isPresent()) {
            Build build1 = optionalBuild.get();
            build1.setAbilities(abilities);
            buildRepository.update(build1);
        }
    }
}
