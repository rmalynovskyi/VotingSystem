package javaops.votingsystem.repository.menu;

import javaops.votingsystem.model.Menu;
import javaops.votingsystem.repository.AbstractRepositoryTest;
import javaops.votingsystem.repository.DishTestData;
import javaops.votingsystem.repository.MenuRepository;
import javaops.votingsystem.repository.MenuTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static javaops.votingsystem.repository.DishTestData.*;
import static javaops.votingsystem.repository.MenuTestData.*;
import static javaops.votingsystem.repository.RestaurantTestData.RESTAURANT1_ID;
import static org.junit.jupiter.api.Assertions.*;

class DataJpaMenuRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;

    @Test
    void create() {
        Menu newMenu = MenuTestData.getNew();
        Menu created = menuRepository.save(newMenu, RESTAURANT1_ID);
        int newId = created.id();
        newMenu.setId(newId);
        assertMatch(created, newMenu);
        assertMatch(menuRepository.get(newId, RESTAURANT1_ID), newMenu);
    }

    @Test
    void update() {
        Menu updated = MenuTestData.getUpdated();
        menuRepository.save(updated, RESTAURANT1_ID);
        assertMatch(menuRepository.get(MENU1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    void updateNotFound() {
        Menu updated = MenuTestData.getUpdated();
        assertNull(menuRepository.save(updated, RESTAURANT1_ID + 1));
    }

    @Test
    void delete() {
        menuRepository.delete(MENU1_ID, RESTAURANT1_ID);
        assertNull(menuRepository.get(MENU1_ID, RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() {
        assertFalse(menuRepository.delete(MENU1_ID - 1, RESTAURANT1_ID));
    }

    @Test
    void deleteNotOwn() {
        assertFalse(menuRepository.delete(MENU1_ID, RESTAURANT1_ID + 1));
    }

    @Test
    void get() {
        Menu actual = menuRepository.get(MENU1_ID, RESTAURANT1_ID);
        assertMatch(actual, MENU1);
    }

    @Test
    void getNotOwn() {
        assertNull(menuRepository.get(MENU1_ID, RESTAURANT1_ID + 1));
    }

    @Test
    void getWithDishesForToday() {
        Menu actual = menuRepository.getWithDishesForToday(RESTAURANT1_ID);
        assertMatch(actual, MENU1);
        DishTestData.assertMatch(actual.getDishes(), DISHES_MENU1);
    }

    @Test
    void getWithDishes() {
        Menu actual = menuRepository.getWithDishes(MENU1_ID + 1, RESTAURANT1_ID);
        assertMatch(actual, MENU2);
        DishTestData.assertMatch(actual.getDishes(), DISHES_MENU2);
    }

    @Test
    void getAll() {
        List<Menu> menuList = menuRepository.getAll(RESTAURANT1_ID + 2);
        assertMatch(menuList, MENUS_RESTAURANT3);
    }

    @Test
    void getAllWithDishes() {
        List<Menu> menuList = menuRepository.getAllWithDishes(RESTAURANT1_ID + 1);
        assertMatch(menuList, MENUS_RESTAURANT2);
        DishTestData.assertMatch(menuList.get(0).getDishes(), DISHES_MENU3);
        DishTestData.assertMatch(menuList.get(1).getDishes(), DISHES_MENU4);
    }
}