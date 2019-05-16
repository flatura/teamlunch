package code.flatura.teamlunch.to;

import code.flatura.teamlunch.model.Dish;

public class DishDto {
    private final Dish dish;

    public DishDto(Dish dish) {
        this.dish = dish;
    }

    public Dish getDish() {
        return dish;
    }

    @Override
    public String toString() {
        return "DishDto{" +
                "name=" + dish.getName() +
                "price=" + dish.getPrice() +
                '}';
    }
}
