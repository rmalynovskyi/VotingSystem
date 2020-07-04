package javaops.votingsystem.repository.restaurant;

import javaops.votingsystem.model.Restaurant;
import javaops.votingsystem.repository.RestaurantRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @CacheEvict(value = {"restaurant", "restaurants"}, allEntries = true)
    @Override
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null) {
            return null;
        }
        return crudRestaurantRepository.save(restaurant);
    }

    @CacheEvict(value = {"restaurant", "restaurants"}, allEntries = true)
    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Cacheable("restaurant")
    @Override
    public Restaurant getWithDayMenu(int id) {
        return crudRestaurantRepository.getWithDayMenu(id, LocalDate.now());
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAllWithDayMenu() {
        return crudRestaurantRepository.getAllWithDayMenu(LocalDate.now());
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }
}
