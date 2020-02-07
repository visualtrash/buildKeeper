package com.nick.dao.repositories;

import com.nick.dao.entities.Hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HeroRepository extends AbstractRepository implements BKRepository<Hero> {
    private List<Hero> heroesList;

    public Optional<Hero> get(UUID id) throws Exception {
        for (Hero hero : heroesList) {
            if (hero.getId().equals(id)) {
                return Optional.of(hero);
            }
        }
        return Optional.empty();
    }

    public Optional get(String name) {
        for (Hero hero : heroesList) {
            if (hero.getName().equals(name)) {
                return Optional.of(hero);
            }
        }
        return Optional.empty();
    }

    public List getAll() {
        for (Hero heroOfList : heroesList) {
            System.out.println(heroOfList.getName());
        }
        return heroesList;
    }

    //add hero in List
    public void add(Hero hero) throws Exception {
        heroesList.add(hero);
        update(hero);
    }

    public void update(Hero heroToUpdate) throws Exception {
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

        for (Hero currentHero : heroesList)
            if (currentHero.getId().equals(hero.getId())) {
                heroWasFounded = true;

                heroesList.remove(currentHero);
                update(hero);
                break;
            }
        if (!heroWasFounded) {
            throw new Exception("Cannot find the hero");
        }
    }

    public void deleteById(UUID heroId) throws Exception {
        // флаг, был ли найден hero в списке
        boolean heroWasFounded = false;
        for (Hero currentHero : heroesList)
            if (currentHero.getId().equals(heroId)) {
                heroWasFounded = true;

                heroesList.remove(currentHero);

                break;
            }
        if (!heroWasFounded) {
            throw new Exception("Cannot find the item");
        }
    }

    public void removeByName(String nameHero) throws Exception {
        // флаг, был ли найден hero в списке
        boolean heroWasFounded = false;
        for (Hero currentHero : heroesList)
            if (currentHero.getName().equals(nameHero)) {
                heroWasFounded = true;

                heroesList.remove(currentHero);

                break;
            }
        if (!heroWasFounded) {
            throw new Exception("Cannot find the item");
        }
    }

    public static String getSaveFileName() {
        return "heroesList";
    }
}
