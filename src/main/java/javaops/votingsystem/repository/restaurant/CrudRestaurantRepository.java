package javaops.votingsystem.repository.restaurant;

import javaops.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r  FROM Restaurant r left join fetch r.menus where r.id=:id")
    Restaurant getWithMenus(@Param("id") int id);

    @Query("SELECT DISTINCT r FROM Restaurant r left join fetch r.menus order by r.name ASC")
    List<Restaurant> getAllWithMenus();

}
