package code.flatura.teamlunch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Dishes")
public class Dish extends AbstractNamedEntity {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "restaurant_id")
    private UUID restaurantId;

    @Column(name = "price")
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
