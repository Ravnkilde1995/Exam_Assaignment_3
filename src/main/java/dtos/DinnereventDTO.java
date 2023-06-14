package dtos;

import java.io.Serializable;
import java.util.Objects;

public class DinnereventDTO implements Serializable {
    private final String time;
    private final String location;
    private final String dish;
    private final int price;

    public DinnereventDTO(String time, String location, String dish, int price) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getDish() {
        return dish;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DinnereventDTO entity = (DinnereventDTO) o;
        return Objects.equals(this.time, entity.time) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.dish, entity.dish) &&
                Objects.equals(this.price, entity.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, location, dish, price);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "time = " + time + ", " +
                "location = " + location + ", " +
                "dish = " + dish + ", " +
                "price = " + price + ")";
    }
}
