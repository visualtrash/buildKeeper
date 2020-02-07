package com.nick.dao.services;

import com.nick.dao.entities.Hero;
import com.nick.dao.repositories.HeroRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HeroService {

    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public void add(Hero hero) throws Exception {
        heroRepository.add(hero);
    }


    public void removeById(UUID itemId) throws Exception {
        heroRepository.deleteById(itemId);
    }

    public void setHeroPosition(Hero hero, String position) throws Exception {
        Optional<Hero> optionalHero = heroRepository.get(hero.getName());

        if (optionalHero.isPresent()) {
            Hero hero1 = optionalHero.get();
            hero1.setPosition(position);
            heroRepository.update(hero1);
        }
    }

    public List getHeroList() {
        return heroRepository.getAll();
    }

    public Optional getHeroById(UUID heroId) throws Exception {
        return heroRepository.get(heroId);
    }
}
