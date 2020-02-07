package com.nick.dao.repositories;

import com.nick.dao.entities.Hero;

import java.util.List;
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

    public List getAll() {
        for (Hero heroOfList : data) {
            System.out.println(heroOfList.getName());
        }
        return data;
    }

    //add hero in List
    public void add(Hero hero) {
        data.add(hero);
        update(hero);
    }

    public void update(Hero heroToUpdate) {
        Optional<Hero> optionalHero = get(heroToUpdate.getId());

        if (optionalHero.isPresent()) {
            Hero hero1 = optionalHero.get();
            hero1.setName(heroToUpdate.getName());
            hero1.setPosition(heroToUpdate.getPosition());

        } else System.out.println("item not found(null)");

    }


    public void delete(Hero hero) throws Exception {
        // флаг, был ли найден hero в списке
        boolean heroWasFounded = false;

        for (Hero currentHero : data)
            if (currentHero.getId().equals(hero.getId())) {
                heroWasFounded = true;

                data.remove(currentHero);
                update(hero);
                break;
            }
        if (!heroWasFounded) {
            throw new Exception("Cannot find the hero");
        }
    }

    public String getSaveFileName() {
        return "data";
    }
}
