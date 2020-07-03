package javaops.votingsystem.repository.menu;

import javaops.votingsystem.model.Menu;
import javaops.votingsystem.repository.MenuRepository;
import javaops.votingsystem.repository.MenuTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static javaops.votingsystem.repository.DishTestData.*;
import static javaops.votingsystem.repository.MenuTestData.*;
import static javaops.votingsystem.repository.RestaurantTestData.RESTAURANT1_ID;

@SpringJUnitConfig(locations = "classpath:spring/spring-db.xml")
@Sql(scripts = "classpath:db/populateDB_hsql.sql", config = @SqlConfig(encoding = "UTF-8"))
class DataJpaMenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    void create() {
        Menu newMenu = MenuTestData.getNew();
        Menu created = menuRepository.save(newMenu, RESTAURANT1_ID);
        int newId = created.id();
        newMenu.setId(newId);
        Assertions.assertEquals(newMenu, created);
        Assertions.assertEquals(newMenu, menuRepository.get(newId, RESTAURANT1_ID));
    }

    @Test
    void update() {
        Menu updated = MenuTestData.getUpdated();
        menuRepository.save(updated, RESTAURANT1_ID);
        Assertions.assertEquals(updated, menuRepository.get(MENU1_ID, RESTAURANT1_ID));
    }

    @Test
    void updateNotFound() {
        Menu updated = MenuTestData.getUpdated();
        Assertions.assertNull(menuRepository.save(updated, RESTAURANT1_ID + 1));
    }

    @Test
    void delete() {
        menuRepository.delete(MENU1_ID, RESTAURANT1_ID);
        Assertions.assertNull(menuRepository.get(MENU1_ID, RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() {
        menuRepository.delete(MENU1_ID, RESTAURANT1_ID);
        Assertions.assertNull(menuRepository.get(MENU1_ID, RESTAURANT1_ID));
    }

    @Test
    void deleteNotOwn() {
        Assertions.assertFalse(menuRepository.delete(MENU1_ID, RESTAURANT1_ID + 1));
    }

    @Test
    void get() {
        Menu actual = menuRepository.get(MENU1_ID, RESTAURANT1_ID);
        Assertions.assertEquals(MENU1, actual);
    }

    @Test
    void getNotOwn() {
        Assertions.assertNull(menuRepository.get(MENU1_ID, RESTAURANT1_ID + 1));
    }

    @Test
    void getWithDishesForToday() {
        Menu actual = menuRepository.getWithDishesForToday(RESTAURANT1_ID);
        Assertions.assertEquals(MENU1, actual);
        Assertions.assertArrayEquals(DISHES_MENU1.toArray(), actual.getDishes().toArray());
    }

    @Test
    void getWithDishes() {
        Menu actual = menuRepository.getWithDishes(MENU1_ID + 1, RESTAURANT1_ID);
        Assertions.assertEquals(MENU2, actual);
        Assertions.assertArrayEquals(DISHES_MENU2.toArray(), actual.getDishes().toArray());
    }

    @Test
    void getAll() {
        List<Menu> menuList = menuRepository.getAll(RESTAURANT1_ID + 2);
        Assertions.assertArrayEquals(MENUS_RESTAURANT3.toArray(), menuList.toArray());
    }

    @Test
    void getAllWithDishes() {
        List<Menu> menuList = menuRepository.getAllWithDishes(RESTAURANT1_ID + 1);
        Assertions.assertArrayEquals(MENUS_RESTAURANT2.toArray(), menuList.toArray());
        Assertions.assertArrayEquals(DISHES_MENU3.toArray(), menuList.get(0).getDishes().toArray());
        Assertions.assertArrayEquals(DISHES_MENU4.toArray(), menuList.get(1).getDishes().toArray());
    }
}