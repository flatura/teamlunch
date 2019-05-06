package code.flatura.teamlunch.model;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    // TODO Сделать threadsafe!

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Set<Dish> dishes;

    @Column(name = "votes")
    BigDecimal votes = new BigDecimal(0);

    @Column(name = "deleted")
    private boolean deleted  = false;

    public Restaurant(String name, Set<Dish> dishes) {
        this.setName(name);
        this.dishes = dishes;
    }

    public int getVotes() {
        return votes.intValue();
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
}
