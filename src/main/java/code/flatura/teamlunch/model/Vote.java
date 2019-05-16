package code.flatura.teamlunch.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *  JavaBean used for save the user vote.
 *  Has a date, user and restaurant properties and also delegated id.
 *  @author Dmitry Morozov for TeamLunch Graduation Project
 */
@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "user_unique_vote_idx")})
public class Vote extends AbstractBaseEntity {

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @NotNull
    @JoinColumn(name = "restaurant_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(@NotNull LocalDate date, @NotNull User user, @NotNull Restaurant restaurant) {
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "date=" + date +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
