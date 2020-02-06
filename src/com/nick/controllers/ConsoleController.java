package com.nick.controllers;

import com.nick.dao.entities.Item;
import com.nick.dao.services.BuildService;
import com.nick.dao.services.HeroService;
import com.nick.dao.services.ItemService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ConsoleController {

    private static BuildService buildService;
    private static HeroService heroService;
    private static ItemService itemService;

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // это входная точка программы, а так же метод служит для выбора пользователем раздела в котором он будет выполнять действия
    public static void main(String[] args) throws Exception {

        while (true) {
            // ввод команды юзера
            System.out.println("What to edit? n\\BUILD(b) / HERO(h) / ITEM(i)");
            String userChoice = reader.readLine().toLowerCase();

            switch (userChoice) {
                case Commands.BUILD_COMMAND:
                    editBuilds();
                    break;
                case Commands.HERO_COMMAND:
                    editHeroes();
                    break;
                case Commands.ITEM_COMMAND:
                    editItems();
                    break;
                case Commands.EXIT_COMMAND:
                    return;
                default:
                    System.out.println("Unknown command :(");
                    break;
            }
        }

    }

    // в этом методе мы обрабатываем все пользовательские действия над итемами
    private static void editItems() throws Exception {
        System.out.println("What do you want to do with ITEMs? n\\ADD(-a) / REMOVE(-r)");
        String userItemCommand = reader.readLine().toLowerCase();

        switch (userItemCommand) {
            case Commands.ADD_COMMAND:
                createItem();
                break;
            case Commands.REMOVE_COMMAND:
                System.out.println("GET ALL items(-g) or GET BY NAME(-gn)");
                String userGetCommand = reader.readLine().toLowerCase();

                switch (userGetCommand) {
                    case Commands.GET_ALL_COMMAND:
                        itemService.getItemList();
                        break;
                    case Commands.GET_BY_NAME_COMMAND:
                        System.out.println("enter the ID of ITEM");
                        UUID userItemId = UUID.fromString(reader.readLine());

                        itemService.getItemById(userItemId);
                        break;
                    default:
                        System.out.println("cannot find the item :(");
                }

                removeItem();
                break;
            case Commands.EXIT_COMMAND:
                // выходим из раздела
                return;
            default:
                System.out.println("Unknown command :(");
                break;
        }
    }

    // в этом методе мы обрабатываем все пользовательские действия над героями
    private static void editHeroes() {
    }

    // в этом методе мы обрабатываем все пользовательские действия над билдами
    private static void editBuilds() throws Exception {
        while (true) {
            System.out.println("What do you want to do with BUILD? n\\ADD(-a) / UPDATE(-u) / REMOVE(-r)");
            String userBuildCommand = reader.readLine().toLowerCase();

            switch (userBuildCommand) {
                case Commands.ADD_COMMAND:
                    createBuild();
                    break;
                case Commands.REMOVE_COMMAND:
                    removeBuild();
                    break;
                case Commands.UPDATE_COMMAND:
                    updateBuild();
                    break;
                case Commands.EXIT_COMMAND:
                    // выходим из раздела
                    return;
                default:
                    System.out.println("Unknown command :(");
                    break;
            }
        }
    }

    private static void updateBuild() {
    }

    private static void removeBuild() {
    }

    // метод спрашивает у пользователя все необходимое для добавления билда
    // и отдает эту информацию в сервис для выполнения логики создания
    private static void createBuild() throws Exception {
        System.out.println("enter the NAME of BUILD");
        String userBuildName = reader.readLine();
        System.out.println("enter the NAME of HERO");
        String userHeroName = reader.readLine();
        System.out.println("enter the POSITION of HERO");
        String userHeroPosition = reader.readLine();

        List<String> userBuildItems = new ArrayList<>();

        // цикл для ввода массива итемов
        itemsEnterCycle:
        while (true) {
            System.out.println("enter the NAME of ITEM");
            String itemName = reader.readLine();

            userBuildItems.add(itemName);

            // цикл для спрашивания "добавить еще?"
            String oneMoreItemName = "y";
            oneMoreCycle:
            while (!oneMoreItemName.equals("y") || !oneMoreItemName.equals("n")) {
                System.out.println("do u want to add one more item? Y/N");
                oneMoreItemName = reader.readLine().toLowerCase();

                // если пользователь ответил нет, то выходим из цикла спрашивания
                if (oneMoreItemName.equalsIgnoreCase("n")) {
                    break itemsEnterCycle;
                } else if (oneMoreItemName.equalsIgnoreCase("y")) {
                    // тут лейбл просто для понимания какой цикл брейкаем
                    break oneMoreCycle;
                } else System.out.println("incorrect. enter Y/N");
            }
        }


        //buildService.add(userBuildName, userHeroName, userHeroPosition, userBuildItems);
    }

    private static void createItem() throws Exception {
        System.out.println("enter the NAME of new ITEM");
        String itemName = reader.readLine().toLowerCase();

        itemService.add(new Item(itemName));
    }

    private static void removeItem() throws Exception {
        System.out.println("enter the ID of ITEM for REMOVE");
        UUID userItemRemoveId = UUID.fromString(reader.readLine());

        itemService.removeById(userItemRemoveId);
    }
}