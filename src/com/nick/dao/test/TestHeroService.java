package com.nick.dao.test;

import com.nick.dao.entities.Hero;
import com.nick.dao.repositories.HeroRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class TestHeroService {
    HeroRepository heroRepository = new HeroRepository();
    private UUID id = getRandomIdHero();

    @Before
    private UUID getRandomIdHero() {
        List<Hero> heroList = heroRepository.getAll();
        return heroList.get(0).getId();
    }

    @Test
    public void add() {
        try {
            heroRepository.add(new Hero("heroName", "heroPosition"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void removeById() {
        try {
            heroRepository.deleteById(id);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public Optional getHeroById() {
        Optional<Hero> hero = heroRepository.get(id);
        if (hero.equals(null)) Assert.fail();
        return hero;
    }
}
