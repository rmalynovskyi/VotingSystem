package javaops.votingsystem.repository.menu;

import javaops.votingsystem.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Menu m left join fetch m.dishes WHERE m.id=:id and m.restaurant.id=:restaurantId")
    Menu getWithDishes(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId ORDER BY m.localDate DESC")
    List<Menu> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT distinct m FROM Menu m left join fetch m.dishes WHERE m.restaurant.id=:restaurantId ORDER BY m.localDate DESC")
    List<Menu> getAllWithDishes(@Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Menu m left join fetch m.dishes WHERE m.localDate=:localDate and m.restaurant.id=:restaurantId")
    Menu getMenuWithDishesForToday(@Param("localDate") LocalDate localDate, @Param("restaurantId") int restaurantId);

}
