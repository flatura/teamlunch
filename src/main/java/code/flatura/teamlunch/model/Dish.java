package code.flatura.teamlunch.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * JavaBean used for everyday filling the menu.
 * Has a date, restaurant, price properties and also delegated name and id.
 * @author Dmitry Morozov for TeamLunch Graduation Project
 */
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id", "date"}, name = "unique_dish_idx")})
public class Dish extends AbstractNamedEntity {

    @NotNull
    @Column(name = "date")
    LocalDate date;

    // TODO insert reference instead of int
    @NotNull
    @JoinColumn(name = "restaurant_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    public Dish(@NotNull Integer id, @NotNull Restaurant restaurant, @NotNull BigDecimal price) {

        this.restaurant = restaurant;
        this.price = price;
    }

    public Dish(@NotNull LocalDate date, @NotNull Restaurant restaurant, @NotNull BigDecimal price) {
        this.date = date;
        this.restaurant = restaurant;
        this.price = price;
    }

    public Dish() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "date=" + date +
                ", restaurant=" + restaurant +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    //TODO add cache for Restaurant
}