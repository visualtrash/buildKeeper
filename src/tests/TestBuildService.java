package tests;

import com.nick.dal.entities.Build;
import com.nick.dal.entities.Hero;
import com.nick.dal.entities.Item;
import com.nick.dal.entities.Rune;
import com.nick.dal.repositories.BuildRepository;
import com.nick.enums.Ability;
import com.nick.services.BuildService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestBuildService {
    private UUID setId = null;

    @Test
    public void tryAddBuild() {
        BuildService buildService = new BuildService(new BuildRepository());

        add(buildService);
        List<Build> list = buildService.getBuildList();

        for (Build eachBuild : list) {
            if (!eachBuild.getName().equals("userBuildName")) {
                Assert.fail();
            }
        }
    }

    @Test
    public void tryGetAllBuilds() {
        BuildService buildService = new BuildService(new BuildRepository());
        try {
            // первая фаза теста - подготовка данных
            for (int i = 0; i < 5; i++) {
                add(buildService);
            }

            // вторая фаза теста - проверка ожидаемого результата
            List buildList = buildService.getBuildList();
            Assert.assertEquals(5, buildList.size());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryRemoveBuild() {
        BuildService buildService = new BuildService(new BuildRepository());

        //add
        add(buildService);

        //remove
        UUID buildId = null;
        List<Build> list = buildService.getBuildList();

        for (Build eachBuild : list) {
            if (eachBuild.getName().equals("userBuildName")) {
                buildId = eachBuild.getId();
            }
        }

        try {
            buildService.removeById(buildId);
            for (Build eachBuild : list) {
                if (eachBuild.getId().equals(buildId)) {
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryRenameBuild() {
        BuildService buildService = new BuildService(new BuildRepository());

        add(buildService);

        List<Build> list = buildService.getBuildList();
        try {
            buildService.updateName(setId, "newName");

            for (Build eachBuild : list) {
                if (!eachBuild.getId().equals(setId) && !eachBuild.getName().equals("newName")) {
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryEditHeroPosition() {
        BuildService buildService = new BuildService(new BuildRepository());

        add(buildService);

        buildService.editPosition(setId, "top");

        List<Build> list = buildService.getBuildList();
        for (Build eachBuild : list) {
            if (eachBuild.getId().equals(setId) && !eachBuild.getHeroPosition().equals("top")) {
                Assert.fail();
            }
        }
    }

    @Test
    public void tryUpdateItems() {
        BuildService buildService = new BuildService(new BuildRepository());

        add(buildService);

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("cool hat"));
        try {
            buildService.updateItems(setId, itemList);

            List<Build> list = buildService.getBuildList();
            for (Build eachBuild : list) {
                if (eachBuild.getId().equals(setId)) {
                    List<Item> editedItemList = eachBuild.getItems();

                    for (Item eachItem : editedItemList) {
                        if (!eachItem.getName().equals("cool hat")) {
                            Assert.fail();
                        }
                    }

                }
            }
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryUpdateHeroInBuild() {
        BuildService buildService = new BuildService(new BuildRepository());

        add(buildService);

        Hero newHero = new Hero("newHero");
        try {
            buildService.updateHero(setId, newHero);

            List<Build> list = buildService.getBuildList();
            for (Build eachBuild : list) {
                if (eachBuild.getId().equals(setId) && !eachBuild.getHero().getName().equals("newHero")) {
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryUpdateAbilitiesInBuild() {
        BuildService buildService = new BuildService(new BuildRepository());

        add(buildService);

        List<Build> buildList = buildService.getBuildList();

        List<Ability> newAbilities = new ArrayList<>();
        newAbilities.add(Ability.E);
        newAbilities.add(Ability.W);
        newAbilities.add(Ability.Q);
        newAbilities.add(Ability.Q);
        newAbilities.add(Ability.E);
        newAbilities.add(Ability.R);

        try {
            buildService.updateAbilities(setId, newAbilities);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }

        for (Build eachBuild : buildList) {
            if (eachBuild.getId().equals(setId)) {
                List<Ability> editedAbilitiesList = eachBuild.getAbilities();
                if (!editedAbilitiesList.equals(newAbilities)) {
                    Assert.fail();
                }
            }
        }
    }

    //add build for test
    private void add(BuildService buildService) {
        Hero hero = new Hero("Hero");

        List<Item> itemList = new ArrayList<>();
        Item item = new Item("boots");
        itemList.add(item);

        Rune rune = new Rune();

        List<Ability> abilities = new ArrayList<>();
        abilities.add(Ability.Q);

        buildService.add("userBuildName", hero, itemList, rune, abilities, "jungle");

        List<Build> list = buildService.getBuildList();

        //упрощение поиска по id
        String newStringId = "26dff229-de37-4e85-9a83-3d4800132038";
        setId = UUID.fromString(newStringId);

        for (Build eachBuild : list) {
            if (eachBuild.getName().equals("userBuildName")) {
                eachBuild.setId(setId);
            }
        }
    }
}

