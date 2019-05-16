package code.flatura.teamlunch.web;

import code.flatura.teamlunch.model.Dish;
import code.flatura.teamlunch.service.DishService;
import code.flatura.teamlunch.to.DishDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/api/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
// TODO Add RequestMapping(... produces = MediaType.APPLICATION_JSON_VALUE) to all controllers
public class DishController {
    private DishService dishService;

    public DishController() {
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<Dish>> getAll() {
        return new ResponseEntity<>(dishService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<DishDto> retrieve(@PathVariable("id") int id) {
        return dishService.getById(id)
                .map(d -> new ResponseEntity<>(new DishDto(d), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        dish.setId(null);
        return new ResponseEntity<>(dishService.create(dish), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/update", method = PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish newDish, @PathVariable("id") int id) {
        dishService.update(newDish, id);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        dishService.delete(id);
    }

    // TODO Entity <-> DTO converters
}
