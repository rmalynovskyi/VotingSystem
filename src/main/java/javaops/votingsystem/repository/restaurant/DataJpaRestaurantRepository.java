package javaops.votingsystem.repository.restaurant;

import javaops.votingsystem.model.Restaurant;
import javaops.votingsystem.repository.RestaurantRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public Restaurant getWithMenus(int id) {
        return crudRestaurantRepository.getWithMenus(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getAllWithMenus() {
        return crudRestaurantRepository.getAllWithMenus();
    }

}
