package javaops.votingsystem.repository;

import javaops.votingsystem.model.Menu;

import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu, int restaurantId);

    boolean delete(int id, int restaurantId);

    Menu get(int id, int restaurantId);

    Menu getWithDishes(int id, int restaurantId);

    List<Menu> getAll(int restaurantId);

    List<Menu> getAllWithDishes(int restaurantId);
}
