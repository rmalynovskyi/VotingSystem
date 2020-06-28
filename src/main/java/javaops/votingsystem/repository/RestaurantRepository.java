package javaops.votingsystem.repository;

import javaops.votingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    Restaurant getWithDayMenu(int id);

    List<Restaurant> getAll();

    List<Restaurant> getAllWithDayMenu();
}
