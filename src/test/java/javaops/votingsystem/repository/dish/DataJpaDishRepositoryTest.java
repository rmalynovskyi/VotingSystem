package javaops.votingsystem.repository.dish;

import javaops.votingsystem.model.Dish;
import javaops.votingsystem.repository.AbstractRepositoryTest;
import javaops.votingsystem.repository.DishRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static javaops.votingsystem.repository.DishTestData.*;
import static javaops.votingsystem.repository.MenuTestData.MENU1_ID;
import static org.junit.jupiter.api.Assertions.*;

class DataJpaDishRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private DishRepository dishRepository;

    @Test
    void create() {
        Dish newDish = getNew();
        Dish created = dishRepository.save(newDish, MENU1_ID);
        int newId = created.id();
        newDish.setId(newId);
        assertMatch(created, newDish);
        assertMatch(dishRepository.get(newId, MENU1_ID), newDish);
    }

    @Test
    void update() {
        Dish updated = getUpdated();
        dishRepository.save(updated, MENU1_ID);
        assertMatch(dishRepository.get(DISH1_ID, MENU1_ID), updated);
    }

    @Test
    void updateNotFound() {
        Dish updated = getUpdated();
        assertNull(dishRepository.save(updated, MENU1_ID + 5));
    }

    @Test
    void delete() {
        dishRepository.delete(DISH1_ID + 3, MENU1_ID + 1);
        assertNull(dishRepository.get(DISH1_ID + 3, MENU1_ID + 1));
    }

    @Test
    void deleteNotFound() {
        assertFalse(dishRepository.delete(1, MENU1_ID));
    }

    @Test
    void deleteNotOwn() {
        assertFalse(dishRepository.delete(DISH1_ID + 1, MENU1_ID + 5));
    }

    @Test
    void get() {
        Dish actual = dishRepository.get(DISH1_ID, MENU1_ID);
        assertMatch(actual, DISH1);
    }

    @Test
    void getNotOwn() {
        assertNull(dishRepository.get(DISH1_ID + 3, MENU1_ID + 4));
    }

    @Test
    void getAll() {
        List<Dish> dishList = dishRepository.getAll(MENU1_ID + 1);
        assertMatch(dishList, DISHES_MENU2);
    }
}