package javaops.votingsystem.repository.dish;

import javaops.votingsystem.model.Dish;
import javaops.votingsystem.repository.DishRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static javaops.votingsystem.repository.DishTestData.*;
import static javaops.votingsystem.repository.MenuTestData.MENU1_ID;


@SpringJUnitConfig(locations = "classpath:spring/spring-db.xml")
@Sql(scripts = "classpath:db/populateDB_hsql.sql", config = @SqlConfig(encoding = "UTF-8"))
class DataJpaDishRepositoryTest {

    @Autowired
    private DishRepository dishRepository;

    @Test
    void create() {
        Dish newDish = getNew();
        Dish created = dishRepository.save(newDish, MENU1_ID);
        int newId = created.id();
        newDish.setId(newId);
        Assertions.assertEquals(newDish, created);
        Assertions.assertEquals(newDish, dishRepository.get(newId, MENU1_ID));
    }

    @Test
    void update() {
        Dish updated = getUpdated();
        dishRepository.save(updated, MENU1_ID);
        Assertions.assertEquals(updated, dishRepository.get(DISH1_ID, MENU1_ID));
    }

    @Test
    void updateNotFound() {
        Dish updated = getUpdated();
        Assertions.assertNull(dishRepository.save(updated, MENU1_ID + 5));
    }

    @Test
    void delete() {
        dishRepository.delete(DISH1_ID + 3, MENU1_ID + 1);
        Assertions.assertNull(dishRepository.get(DISH1_ID + 3, MENU1_ID + 1));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertFalse(dishRepository.delete(1, MENU1_ID));
    }

    @Test
    void deleteNotOwn() {
        Assertions.assertFalse(dishRepository.delete(DISH1_ID + 1, MENU1_ID + 5));
    }

    @Test
    void get() {
        Dish actual = dishRepository.get(DISH1_ID, MENU1_ID);
        Assertions.assertEquals(DISH1, actual);
    }

    @Test
    void getNotOwn() {
        Assertions.assertNull(dishRepository.get(DISH1_ID + 3, MENU1_ID + 4));
    }

    @Test
    void getAll() {
        List<Dish> dishList = dishRepository.getAll(MENU1_ID + 1);
        Assertions.assertArrayEquals(DISHES_MENU2.toArray(), dishList.toArray());
    }
}