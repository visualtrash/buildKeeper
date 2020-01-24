package com.nick.dao.services;

import com.nick.dao.entities.Hero;

import java.util.List;
import java.util.UUID;

public class HeroService {

    private List<Hero> heroesList;

    public HeroService(List<Hero> heroesList) {
        this.heroesList = heroesList;
    }

    public Hero add(String name, String position) {
        Hero hero = new Hero(name, position);

        heroesList.add(hero);

        return hero;
    }

    public void remove(UUID id) throws Exception {
        // флаг, обозначающий был ли найден герой в списке
        boolean heroWasFounded = false;

        for (Hero hero : heroesList) {
            if (hero.getId().equals(id)) {
                heroWasFounded = true;

                heroesList.remove(hero);
                break;
            }
        }

        if (!heroWasFounded) {
            throw new Exception("Cannot find the hero for ID = " + id);
        }
    }
}
