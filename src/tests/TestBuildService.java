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

public class TestBuildService {

    @Test
    public void tryAddBuild() {
        BuildService buildService = new BuildService(new BuildRepository());

        Build build = createTestBuild();
        buildService.add(build);

        List<Build> list = buildService.getBuildList();
        for (Build eachBuild : list) {
            if (!eachBuild.getName().equals(build.getName())) {
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
                buildService.add(createTestBuild());
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
        Build build = createTestBuild();
        buildService.add(build);

        //remove
        List<Build> list = buildService.getBuildList();

        try {
            buildService.removeById(build.getId());

            for (Build eachBuild : list) {
                if (eachBuild.getId().equals(build.getId())) {
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

        Build build = createTestBuild();
        buildService.add(build);

        List<Build> list = buildService.getBuildList();
        try {
            buildService.updateName(build.getId(), "newName");

            for (Build eachBuild : list) {
                if (eachBuild.getId().equals(build.getId()) && !eachBuild.getName().equals("newName")) {
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

        Build build = createTestBuild();
        buildService.add(createTestBuild());

        buildService.editPosition(build.getId(), "top");

        List<Build> list = buildService.getBuildList();
        for (Build eachBuild : list) {
            if (eachBuild.getId().equals(build.getId()) && !eachBuild.getHeroPosition().equals("top")) {
                Assert.fail();
            }
        }
    }

    @Test
    public void tryUpdateItems() {
        BuildService buildService = new BuildService(new BuildRepository());

        Build build = createTestBuild();
        buildService.add(build);

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("cool hat"));
        try {
            buildService.updateItems(build.getId(), itemList);

            List<Build> list = buildService.getBuildList();
            for (Build eachBuild : list) {
                if (eachBuild.getId().equals(build.getId())) {
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

        Build build = createTestBuild();
        buildService.add(build);

        Hero newHero = new Hero("newHero");
        try {
            buildService.updateHero(build.getId(), newHero);

            List<Build> list = buildService.getBuildList();
            for (Build eachBuild : list) {
                if (eachBuild.getId().equals(build.getId()) && !eachBuild.getHero().getName().equals("newHero")) {
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

        Build build = createTestBuild();
        buildService.add(build);

        List<Build> buildList = buildService.getBuildList();

        List<Ability> newAbilities = new ArrayList<>();
        newAbilities.add(Ability.E);
        newAbilities.add(Ability.W);
        newAbilities.add(Ability.Q);
        newAbilities.add(Ability.Q);
        newAbilities.add(Ability.E);
        newAbilities.add(Ability.R);

        try {
            buildService.updateAbilities(build.getId(), newAbilities);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }

        for (Build eachBuild : buildList) {
            if (eachBuild.getId().equals(build.getId())) {
                List<Ability> editedAbilitiesList = eachBuild.getAbilities();
                if (!editedAbilitiesList.equals(newAbilities)) {
                    Assert.fail();
                }
            }
        }
    }

    private Build createTestBuild() {
        Hero hero = new Hero("Hero");

        List<Item> itemList = new ArrayList<>();
        Item item = new Item("boots");
        itemList.add(item);

        Rune rune = new Rune();

        List<Ability> abilities = new ArrayList<>();
        abilities.add(Ability.Q);

        return new Build("buildName", hero, itemList, rune, abilities, "userHeroPosition");
    }
}

