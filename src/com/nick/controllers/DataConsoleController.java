package com.nick.controllers;

import com.nick.dao.entities.Build;
import com.nick.dao.entities.Hero;
import com.nick.dao.entities.Item;
import com.nick.dao.entities.Rune;
import com.nick.dao.repositories.BuildRepository;
import com.nick.dao.repositories.HeroRepository;
import com.nick.dao.repositories.ItemRepository;
import com.nick.enums.Ability;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class DataConsoleController {

    private static BuildRepository buildRepository;
    private static HeroRepository heroRepository;
    private static ItemRepository itemRepository;

    public static void main(String[] args) throws Exception {


        boolean isRepeat = true;
        List<Item> userItems = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        outerCycle:
        while (true) {
            // ввод команды юзера
            System.out.println("please enter the command");
            String userCommand = reader.readLine().toUpperCase();

            switch (userCommand) {
                case ConsoleController.ADD_COMMAND:
                    System.out.println("what do you want to add?");
                    String userAddCommand = reader.readLine();

                    if (userAddCommand.equalsIgnoreCase("build")) {
                        //name build
                        System.out.println("enter the NAME of BUILD");
                        String userBuildName = reader.readLine();
                        //name hero
                        System.out.println("enter the NAME of HERO");
                        String userHeroName = reader.readLine();
                        //position
                        System.out.println("enter the POSITION of HERO");
                        String userHeroPosition = reader.readLine();
                        while (isRepeat) {
                            System.out.println("enter the NAME of ITEM");

                            String itemName = reader.readLine();
                            Item item = new Item(itemName);
                            userItems.add(item);

                            while (true) {
                                System.out.println("do u want to add one more item? Y/N");
                                String userAnswerToAddItem = reader.readLine();
                                if (userAnswerToAddItem.equalsIgnoreCase("n")) {
                                    isRepeat = false;
                                    break;
                                } else System.out.println("incorrect. enter Y/N");
                            }
                        }
                        // TODO: runes + abilities

                        Build build = new Build(userBuildName, new Hero(userHeroName, userHeroPosition), userItems, new Rune(), Ability.Q);
                        addBuildInRepository(build);

                        System.out.println("snippet was successfully added");
                    }
                    if (userAddCommand.equalsIgnoreCase("hero")) {
                        System.out.println("enter the NAME of HERO");
                        String userHeroName = reader.readLine();
                        System.out.println("enter the POSITION of HERO");
                        String userHeroPosition = reader.readLine();

                        Hero hero = new Hero(userHeroName, userHeroPosition);
                        addHeroToRepository(hero);

                        System.out.println("hero was successfully added");
                    }

                    if (userAddCommand.equalsIgnoreCase("item")) {
                        System.out.println("enter the NAME of ITEM");
                        String userItemName = reader.readLine();

                        Item item = new Item(userItemName);
                        addItemToRepository(item);

                        System.out.println("item was successfully added");
                    }
                    break;
                case ConsoleController.REMOVE_COMMAND:

                    break;
                case ConsoleController.UPDATE_COMMAND:

                    break;

                case ConsoleController.EXIT_COMMAND:
                    break outerCycle;
            }
        }

    }

    private static void addBuildInRepository(Build build) throws Exception {
        buildRepository.add(build);
    }

    private static void addHeroToRepository(Hero hero) throws Exception {
        heroRepository.add(hero);
    }

    private static void addItemToRepository(Item item) throws Exception {
        itemRepository.add(item);
    }
}
