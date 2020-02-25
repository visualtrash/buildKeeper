package com.nick.services;

import com.nick.dal.entities.Hero;
import com.nick.dal.repositories.HeroRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HeroService {

    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    // удобно когда метод добавления сущности потом возвращает новую созданную сущность обратно
    public Hero add(String heroName) throws Exception {
        Hero hero = new Hero(heroName);
        heroRepository.add(hero);

        return hero;
    }

    public void removeById(UUID heroId) throws Exception {
        heroRepository.deleteById(heroId);
    }

    public void removeByName(String heroName) throws Exception {
        heroRepository.deleteByName(heroName);
    }

    public List getHeroList() {
        return heroRepository.getAll();
    }

    public Optional<Hero> getHeroById(UUID heroId) {
        return heroRepository.get(heroId);
    }

    public Optional<Hero> getHeroByName(String name) {
        return heroRepository.get(name);
    }

    public void editHeroName(UUID id, String newName) {
        List<Hero> list = heroRepository.getAll();
        for (Hero eachHero : list) {
            if (eachHero.getId().equals(id)) {
                eachHero.setName(newName);
            }
        }
    }
}
