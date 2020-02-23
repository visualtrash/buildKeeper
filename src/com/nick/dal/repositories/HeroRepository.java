package com.nick.dal.repositories;

import com.google.gson.reflect.TypeToken;
import com.nick.dal.entities.Hero;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class HeroRepository extends AbstractRepository<Hero> {

    public Optional<Hero> get(UUID id) {
        for (Hero hero : data) {
            if (hero.getId().equals(id)) {
                return Optional.of(hero);
            }
        }
        return Optional.empty();
    }

    public Optional get(String name) {
        for (Hero hero : data) {
            if (hero.getName().equals(name)) {
                return Optional.of(hero);
            }
        }
        return Optional.empty();
    }

    //add hero in List
    public void add(Hero hero) throws IOException {
        data.add(hero);
        persist();
        System.out.println("successful added");
    }

    public void update(Hero heroToUpdate) {
        Optional<Hero> optionalHero = get(heroToUpdate.getId());

        if (optionalHero.isPresent()) {
            Hero hero1 = optionalHero.get();
            hero1.setName(heroToUpdate.getName());
        } else System.out.println("item not found(null)");

    }

    @Override
    public String getSaveFileName() {
        return "heroes";
    }

    @Override
    Type getRepositoryEntityListType() {
        return new TypeToken<ArrayList<Hero>>(){}.getType();
    }
}
