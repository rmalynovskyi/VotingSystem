package javaops.votingsystem.repository.restaurant;

import javaops.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r left join fetch r.menus where r.id=:id")
    Restaurant getWithMenus(@Param("id") int id);
}
