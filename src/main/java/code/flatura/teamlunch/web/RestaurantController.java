package code.flatura.teamlunch.web;

import code.flatura.teamlunch.model.Dish;
import code.flatura.teamlunch.model.Restaurant;
import code.flatura.teamlunch.service.DishService;
import code.flatura.teamlunch.service.RestaurantService;
import code.flatura.teamlunch.to.DishDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/api/restaurants")
public class RestaurantController {
    private RestaurantService restaurantService;
    private DishService dishService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<Restaurant>> getAllEnabled() {
        return restaurantService.getAllEnabled()
                .map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Restaurant> getById(@PathVariable("id") Integer id) {
        return restaurantService.getById(id)
                .map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}/dishes", method = GET)
    public ResponseEntity<List<DishDto>> getDishes(@PathVariable("id") Integer id, @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Restaurant restaurant = restaurantService.getById(id).orElse(null);
        if (date == null) {
            date = LocalDate.now();
        }
        if (restaurant != null) {
            List<Dish> dishes = dishService.getByRestaurantAndDate(restaurant, date);
            if (dishes.size() > 0) {
                return new ResponseEntity<>(dishes.stream()
                        .map(d -> new DishDto(d))
                        .collect(Collectors.toList()),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        restaurant.setId(null); // TODO Better to use DTO, but have no time
        Restaurant createdRestaurant = restaurantService.create(restaurant);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.OK);
    }

    @Transactional
    @Modifying
    @RequestMapping(value = "/{id}/update", method = PUT)
    public ResponseEntity updateById(@PathVariable("id") Integer id, @RequestBody Restaurant newRestaurant) {
        restaurantService.update(newRestaurant);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional
    @Modifying
    @RequestMapping(value = "/{id}/delete", method = DELETE)
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        restaurantService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    // TODO Replace Services with Repositories (every Controller)
}
