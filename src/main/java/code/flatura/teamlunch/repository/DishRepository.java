package code.flatura.teamlunch.repository;

import code.flatura.teamlunch.model.Dish;
import code.flatura.teamlunch.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * JavaBean used for communicate with datasource.
 * Has methods to manipulate Dish entities
 *
 * @author Dmitry Morozov for TeamLunch Graduation Project
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT d FROM Dish d WHERE d.date=:date")
    Optional<List<Dish>> findByDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @Transactional(readOnly = true)
    @Query("SELECT d FROM Dish d WHERE d.restaurant=:restaurant AND d.date=:date")
    List<Dish> findByRestaurantAndDate(@Param("restaurant") Restaurant restaurant, @Param("date") LocalDate date);

    @Override
    Dish save(Dish dish);
}
