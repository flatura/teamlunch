package code.flatura.teamlunch.repository;

import code.flatura.teamlunch.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * JavaBean used for communicate with datasource.
 * Has methods to manipulate Restaurant entities
 *
 * @author Dmitry Morozov for TeamLunch Graduation Project
 */

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE CONCAT('%',name,'%')")
    Optional<Restaurant> findByName(@Param("name") String name);

    @Query("SELECT r FROM Restaurant r WHERE r.enabled = TRUE ORDER by r.name")
    Optional<List<Restaurant>> getAllEnabled();

    @Transactional
    @Modifying
    @Override
    Restaurant save(Restaurant restaurant);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") Integer id);

    // TODO Mark disabled instead of deleting from DB
}
