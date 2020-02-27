package tests;

import com.nick.dal.entities.Hero;
import com.nick.dal.repositories.HeroRepository;
import com.nick.services.HeroService;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class TestHeroService {

    private static Path source = Paths.get("saveData");
    private static Path newDir = Paths.get("saveData_temp");

    private UUID setId = null;

    // мы хотим создать механизм переноса пользовательских данных во временную папку перед каждым тестом
    @BeforeClass
    public static void prepareData() {
        try {
            if (source.toFile().exists())
                Files.move(source, newDir, REPLACE_EXISTING);
        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }


    @AfterClass
    public static void moveBackData() {
        try {
            if (newDir.toFile().exists())
                Files.move(newDir, source, REPLACE_EXISTING);
        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }

    @After
    public void clearData() {
        Path source = Paths.get("saveData");

        try {
            if (source.toFile().exists())
                Files.walk(source)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryGetHeroByRandomId() {
        HeroService service = new HeroService(new HeroRepository());

        UUID randomId = UUID.randomUUID();
        Optional heroById = service.getHeroById(randomId);

        // найденый результат не содержит героя внутри
        Assert.assertFalse(heroById.isPresent());
    }

    @Test
    public void tryGetHeroByExistingId() {
        HeroService service = new HeroService(new HeroRepository());
        String testHeroName = "TEST_HERO_1";

        try {
            Hero newHero = service.add(testHeroName);

            Hero hero = service.getHeroById(newHero.getId()).get();

            Assert.assertEquals(hero.getId(), newHero.getId());
            Assert.assertEquals(hero.getName(), testHeroName);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryGetAllHeroes() {
        HeroService heroService = new HeroService(new HeroRepository());

        try {
            // первая фаза теста - подготовка данных
            for (int i = 0; i < 50; i++) {
                heroService.add("TEST_HERO_" + i);
            }

            // вторая фаза теста - проверка ожидаемого результата
            List heroList = heroService.getHeroList();
            Assert.assertEquals(50, heroList.size());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryGetNonexistentHeroByName() {
        HeroService heroService = new HeroService(new HeroRepository());

        String notExistHeroName = "notExistHeroName";
        Optional heroByName = heroService.getHeroByName(notExistHeroName);

        // найденый результат не содержит героя внутри
        Assert.assertFalse(heroByName.isPresent());
    }

    @Test
    public void tryGetHeroByName() {
        HeroService heroService = new HeroService(new HeroRepository());

        addHero(heroService);
        try {
            Optional<Hero> getHero = heroService.getHeroByName("heroName");
            if (!getHero.get().getId().equals(setId)) {
                Assert.fail();
            }

        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryAddHero() {
        HeroService heroService = new HeroService(new HeroRepository());

        addHero(heroService);
        try {
            List<Hero> heroList = heroService.getHeroList();
            for (Hero eachHero : heroList) {
                if (!eachHero.getId().equals(setId)) {
                    Assert.fail();
                }
            }

        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }


    @Test
    public void tryRemoveHeroById() {
        HeroService heroService = new HeroService(new HeroRepository());

        addHero(heroService);
        try {
            heroService.removeById(setId);

            if (heroService.getHeroById(setId).isPresent()) {
                Assert.fail();
            }
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }


    @Test
    public void tryRemoveHeroByName() {
        HeroService heroService = new HeroService(new HeroRepository());

        addHero(heroService);
        try {
            heroService.removeByName("heroName");
            if (heroService.getHeroByName("heroName").isPresent()) {
                Assert.fail();
            }
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryEditHeroName() {
        HeroService heroService = new HeroService(new HeroRepository());

        addHero(heroService);

        String newName = "editedName";
        heroService.editHeroName(setId, newName);

        if (!(heroService.getHeroById(setId)).get().getName().equals(newName)) {
            Assert.fail();
        }
    }

    //add hero for test
    private void addHero(HeroService heroService) {
        try {
            heroService.add("heroName");
        } catch (Exception e) {
            Assert.fail(e.toString());
        }

        List<Hero> list = heroService.getHeroList();

        //упрощение поиска по id
        String newStringId = "26dff229-de37-4e85-9a83-3d4800132038";
        setId = UUID.fromString(newStringId);

        for (Hero eachHero : list) {
            if (eachHero.getName().equals("heroName")) {
                eachHero.setId(setId);
            }
        }
    }
}
