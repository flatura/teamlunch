package code.flatura.teamlunch.service;

import code.flatura.teamlunch.model.Dish;
import code.flatura.teamlunch.model.Restaurant;
import code.flatura.teamlunch.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class DishService {
    private DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public DishService() {
    }

    @Autowired
    public void setDishRepository(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Dish> getByRestaurantAndDate(Restaurant restaurant, LocalDate date) {
        return dishRepository.findByRestaurantAndDate(restaurant, date);
    }

    @Secured({"ROLE_ADMIN"})
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Secured({"ROLE_ADMIN"})
    public Optional<Dish> getById(int id) {
        return dishRepository.findById(id);
    }

    @Secured({"ROLE_ADMIN"})
    public Dish create(Dish newDish) {
        return dishRepository.save(newDish);
    }

    @Secured({"ROLE_ADMIN"})
    public void update(Dish newDish, Integer id) {
        if (newDish.isNew() || getById(id).isPresent()) {
            dishRepository.save(newDish);
        }
    }

    @Secured({"ROLE_ADMIN"})
    public void delete(int id) {
        dishRepository.deleteById(id);
    }
}
