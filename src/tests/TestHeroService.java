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

    // мы хотим создать механизм переноса пользовательских данных во временную папку перед каждым тестом
    @SuppressWarnings("Duplicates")
    @BeforeClass
    public static void prepareData() {
        Path source = Paths.get("saveData");
        Path newDir = Paths.get("saveData_temp");

        try {
            if (source.toFile().exists())
                Files.move(source, newDir, REPLACE_EXISTING);
        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }

    @SuppressWarnings("Duplicates")
    @AfterClass
    public static void moveBackData() {
        Path source = Paths.get("saveData");
        Path newDir = Paths.get("saveData_temp");

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

    // получение героя по id +
    // 1. получить несуществуюшего героя
    // 2. получить существуюшего героя
    // получение всех героев +


    // получение героя по имени +
    // 1. получить несуществуюшего героя
    // 2. получить существуюшего героя
    // добавление героя в список +
    // удаление героя из списка +
    // изменение имени героя -


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
        HeroService service = new HeroService(new HeroRepository());

        try {
            // первая фаза теста - подготовка данных
            for (int i = 0; i < 50; i++) {
                service.add("TEST_HERO_" + i);
            }

            // вторая фаза теста - проверка ожидаемого результата
            List heroList = service.getHeroList();
            Assert.assertEquals(50, heroList.size());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryGetNotExistHeroByName() {
        HeroService service = new HeroService(new HeroRepository());

        String notExistHeroName = "notExistHeroName";
        Optional heroByName = service.getHeroByName(notExistHeroName);

        // найденый результат не содержит героя внутри
        Assert.assertFalse(heroByName.isPresent());
    }

    @Test
    public void tryGetHeroByName() {
        HeroService service = new HeroService(new HeroRepository());

        try {
            Hero newHero = service.add("newHero");

            Hero hero = service.getHeroByName(newHero.getName()).get();

            Assert.assertEquals(hero.getName(), newHero.getName());
            Assert.assertEquals(hero.getId(), newHero.getId());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryAddItem() {
        HeroRepository heroRepository = new HeroRepository();

        Hero hero = new Hero("heroName");

        try {
            heroRepository.add(hero);
            Assert.assertTrue(heroRepository.get(hero.getName()).isPresent());
        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void tryRemoveItemById() {
        HeroRepository heroRepository = new HeroRepository();

        Hero hero = new Hero("heroName");

        try {
            heroRepository.add(hero);
        } catch (IOException e) {
            Assert.fail(e.toString());
        }

        try {
            heroRepository.deleteById(hero.getId());
            Assert.assertFalse(heroRepository.get(hero.getId()).isPresent());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void tryRemoveItemByName() {
        HeroRepository heroRepository = new HeroRepository();

        Hero hero = new Hero("heroName");

        try {
            heroRepository.add(hero);
        } catch (IOException e) {
            Assert.fail(e.toString());
        }

        try {
            heroRepository.deleteByName(hero.getName());
            Assert.assertFalse(heroRepository.get(hero.getName()).isPresent());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void tryEditHeroName() {
        HeroService service = new HeroService(new HeroRepository());

        String heroNameForEdit = "heroNameForEdit";
        String editedName = "editedName";

        Hero hero = null;
        try {
            hero = service.add(heroNameForEdit);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }

        UUID idHero = hero.getId();

        service.editHeroName(idHero, editedName);

        if (hero.getName().equals(heroNameForEdit)) {
            Assert.fail();
        }
    }
}
