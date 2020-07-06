package javaops.votingsystem.repository.restaurant;

import javaops.votingsystem.model.Restaurant;
import javaops.votingsystem.repository.AbstractRepositoryTest;
import javaops.votingsystem.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static javaops.votingsystem.repository.MenuTestData.MENU1;
import static javaops.votingsystem.repository.MenuTestData.MENU_MATCHER;
import static javaops.votingsystem.repository.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class DataJpaRestaurantRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void create() {
        Restaurant newRestaurant = getNew();
        Restaurant created = restaurantRepository.save(newRestaurant);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.get(newId), newRestaurant);
    }

    @Test
    void update() {
        Restaurant updated = getUpdated();
        restaurantRepository.save(updated);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.get(RESTAURANT1_ID), updated);
    }

    @Test
    void updateNotFound() {
        Restaurant updated = getNotExisting();
        assertNull(restaurantRepository.save(updated));
    }

    @Test
    void delete() {
        restaurantRepository.delete(RESTAURANT1_ID);
        assertNull(restaurantRepository.get(RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() {
        assertFalse(restaurantRepository.delete(RESTAURANT1_ID - 1));
    }

    @Test
    void get() {
        Restaurant actual = restaurantRepository.get(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertMatch(actual, RESTAURANT1);
    }

    @Test
    void getWithDayMenu() {
        Restaurant actual = restaurantRepository.getWithDayMenu(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertMatch(actual, RESTAURANT1);
        MENU_MATCHER.assertMatch(actual.getMenus(), Collections.singletonList(MENU1));
    }

    @Test
    void getAllWithDayMenu() {
        List<Restaurant> restaurants = restaurantRepository.getAllWithDayMenu();
        RESTAURANT_MATCHER.assertMatch(restaurants, RESTAURANT_WITH_MENU_FOR_TODAY);
    }

    @Test
    void getAll() {
        List<Restaurant> restaurants = restaurantRepository.getAll();
        RESTAURANT_MATCHER.assertMatch(restaurants, RESTAURANTS);
    }
}