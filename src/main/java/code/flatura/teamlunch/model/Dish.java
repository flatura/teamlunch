package code.flatura.teamlunch.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * JavaBean used for everyday filling the menu.
 * Has a date, restaurant, price properties and also delegated name and id.
 * @author Dmitry Morozov for TeamLunch Graduation Project
 */
@Entity
@Table(name = "dishes", uniqueConstraints = { @UniqueConstraint(columnNames = "name", name = "dishes_name_idx")} )
public class Dish extends AbstractNamedEntity {

    @Column(name = "date")
    @NotNull
    LocalDate date;

    @JoinColumn(name = "restaurant_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    public Dish(UUID id, Restaurant restaurant, BigDecimal price) {

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
}