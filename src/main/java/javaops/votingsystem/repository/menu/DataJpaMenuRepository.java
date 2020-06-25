package javaops.votingsystem.repository.menu;

import javaops.votingsystem.model.Menu;
import javaops.votingsystem.repository.MenuRepository;
import javaops.votingsystem.repository.restaurant.CrudRestaurantRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {
    private final CrudMenuRepository crudMenuRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menu);

    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMenuRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return crudMenuRepository.findById(id).filter(menu -> menu.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public Menu getWithDishesByDate(LocalDate localDate, int restaurantId) {
        return crudMenuRepository.getMenuByLocalDateAndRestaurantId(localDate, restaurantId);
    }

    @Override
    public Menu getWithDishes(int id, int restaurantId) {
        return crudMenuRepository.getWithDishes(id, restaurantId);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return crudMenuRepository.getAll(restaurantId);
    }

    @Override
    public List<Menu> getAllWithDishes(int restaurantId) {
        return crudMenuRepository.getAllWithDishes(restaurantId);
    }
}
