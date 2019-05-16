package code.flatura.teamlunch.model;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.annotation.Order;

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
    /*
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant")
    @OrderBy("date DESC")
    private Set<Dish> dishes;
    */

    @Column(name = "enabled")
    private boolean enabled = true;

    //public Restaurant(String name, Set<Dish> dishes) {
    public Restaurant(String name) {
        this.setName(name);
        //this.dishes = dishes;
    }

    public Restaurant() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Restaurant{" + getId() +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
