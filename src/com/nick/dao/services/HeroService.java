package com.nick.dao.services;

import com.nick.dao.entities.Hero;
import com.nick.dao.repositories.HeroRepository;

import java.util.Optional;

public class HeroService {

    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public void save(Hero hero) throws Exception {
        heroRepository.add(hero);
    }

    public void remove(Hero hero) throws Exception {
        heroRepository.delete(hero);
    }

    @SuppressWarnings("unchecked")
    public void setHeroPosition(Hero hero, String position) throws Exception {
        Optional<Hero> optionalHero = heroRepository.get(hero.getName());

        if (optionalHero.isPresent()) {
            Hero hero1 = optionalHero.get();
            hero1.setPosition(position);
            heroRepository.update(hero1);
        }
    }
}
