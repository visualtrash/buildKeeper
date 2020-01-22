package com.nick.dao.repositories;

import com.nick.dao.entities.Hero;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HeroRepository implements BKRepository<Hero> {
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
    public void save(Hero hero) {
        heroesList.add(hero);
    }

    //save hero on disk
    @SuppressWarnings("Duplicates")
    public void update(Hero hero) {
        StringBuilder result = new StringBuilder();

        for (Hero currentBuild : heroesList) {
            String heroLine =
                    currentBuild.getName() + "<|>" + currentBuild.getPosition() +
                            "<|>" + currentBuild.getId();
            result.append(heroLine).append("\n");
        }

        File file = new File("C://BKrep", "HeroRep.txt");

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(result.toString());
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delete(Hero hero) throws Exception {
        // флаг, был ли найден hero в списке
        boolean heroWasFounded = false;

        for (Hero currentHero : heroesList)
            if (currentHero.getId().equals(hero.getId())) {
                heroWasFounded = true;

                heroesList.remove(currentHero);
                break;
            }
        if (!heroWasFounded) {
            throw new Exception("Cannot find the hero");
        }
    }
}
