package com.nick.dal.entities;

import com.nick.enums.Ability;

import java.util.List;

public class Build extends BKEntity {

    private Hero hero;
    private String heroPosition;
    private List<Item> items;
    private Rune rune;
    private List<Ability> abilities;

    public Build(String name, Hero hero, List<Item> items, Rune rune, List<Ability> ability, String heroPosition) {
        super(name);

        this.hero = hero;
        this.items = items;
        this.rune = rune;
        this.abilities = ability;
        this.heroPosition = heroPosition;
    }


    public String getHeroPosition() {
        return heroPosition;
    }

    public void setHeroPosition(String position) {
        this.heroPosition = position;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Rune getRune() {
        return rune;
    }

    public void setRune(Rune rune) {
        this.rune = rune;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }
}
