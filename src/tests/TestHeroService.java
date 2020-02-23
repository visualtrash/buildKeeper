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
    @BeforeClass
    public static void prepareData() {
        Path source = Paths.get("saveData");
        Path newdir = Paths.get("saveData_temp");

        try {
            if (source.toFile().exists())
                Files.move(source, newdir, REPLACE_EXISTING);
        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }

    @AfterClass
    public static void moveBackData() {
        Path source = Paths.get("saveData");
        Path newdir = Paths.get("saveData_temp");

        try {
            if (newdir.toFile().exists())
                Files.move(newdir, source, REPLACE_EXISTING);
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

}
