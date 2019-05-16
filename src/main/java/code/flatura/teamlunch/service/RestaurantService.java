package code.flatura.teamlunch.service;

import code.flatura.teamlunch.model.Restaurant;
import code.flatura.teamlunch.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Optional<List<Restaurant>> getAllEnabled() {
        return restaurantRepository.getAllEnabled();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Optional<Restaurant> getById(Integer id) {
        return restaurantRepository.findById(id);
    }

    @Secured({"ROLE_ADMIN"})
    public void update(Restaurant newRestaurant) {
        restaurantRepository.save(newRestaurant);
    }

    @Secured({"ROLE_ADMIN"})
    public void delete(Integer id) {
        restaurantRepository.delete(id);
    }

    @Secured({"ROLE_ADMIN"})
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    //TODO Add Exception Handlers (every Service)

    //TODO Add Logging (every Service)
}
