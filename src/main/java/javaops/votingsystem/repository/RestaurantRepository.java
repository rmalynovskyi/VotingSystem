package javaops.votingsystem.repository;

import javaops.votingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    Restaurant getWithMenus(int id);

    List<Restaurant> getAll();

    List<Restaurant> getAllWithMenus();

    List<Restaurant> getAllWithMenuOfDay(LocalDate localDate);
}
