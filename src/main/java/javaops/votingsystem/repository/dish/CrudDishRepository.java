package javaops.votingsystem.repository.dish;

import javaops.votingsystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.menu.id=:menuId")
    int delete(@Param("id") int id, @Param("menuId") int menuId);

    @Query("SELECT d FROM Dish d WHERE d.menu.id=:menuId ORDER BY d.name ASC")
    List<Dish> getAll(@Param("menuId") int menuId);

}
