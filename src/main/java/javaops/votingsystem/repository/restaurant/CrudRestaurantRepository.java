package javaops.votingsystem.repository.restaurant;

import javaops.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r left join fetch r.menus m left join fetch m.dishes " +
            "where r.id=:id and m.date=:localDate order by r.name ASC")
    Restaurant getWithDayMenu(@Param("id") int id, @Param("localDate") LocalDate localDate);

    @Query("SELECT DISTINCT r FROM Restaurant r left join fetch r.menus m left join fetch m.dishes " +
            "where m.date=:localDate order by r.name ASC")
    List<Restaurant> getAllWithDayMenu(@Param("localDate") LocalDate localDate);
}
