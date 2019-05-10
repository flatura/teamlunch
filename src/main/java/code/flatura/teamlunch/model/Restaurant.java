package code.flatura.teamlunch.model;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import java.util.*;

/**
 * JavaBean used for .
 * Has dishes property, deleted flag and also delegated name and id.
 * @author Dmitry Morozov for TeamLunch Graduation Project
 */
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurant_name_idx")})
public class Restaurant extends AbstractNamedEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Set<Dish> dishes;

    @Column(name = "deleted")
    private boolean deleted  = false;

    public Restaurant(String name, Set<Dish> dishes) {
        this.setName(name);
        this.dishes = dishes;
    }

    protected Set<Dish> getDishesInternal() {
        if (this.dishes == null) {
            this.dishes = new HashSet<>();
        }
        return this.dishes;
    }

    public List<Dish> getDishes() {
        List<Dish> sortedDishes = new ArrayList<>(getDishesInternal());
        PropertyComparator.sort(sortedDishes,
                new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedDishes);
    }

    public void addDish(Dish dish) {
        //if (dish.isNew()) {
            getDishesInternal().add(dish);
        //}
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                ", name='" + name + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
