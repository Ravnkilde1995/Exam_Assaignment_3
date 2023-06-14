package dtos;

import entities.Dinnerevent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DinnereventDTO implements Serializable {
    private final Long id;
    private final String time;
    private final String location;
    private final String dish;
    private final int price;

    public DinnereventDTO(Dinnerevent d) {
        this.id = d.getId();
        this.time = d.getTime();
        this.location = d.getLocation();
        this.dish = d.getDish();
        this.price = d.getPrice();
    }

    public static List<DinnereventDTO> getDtos(List<Dinnerevent> events) {
        List<DinnereventDTO> dinnereventDTOS = new ArrayList();
        events.forEach(d -> dinnereventDTOS.add(new DinnereventDTO(d)));
        return dinnereventDTOS;
    }

    public Long getId() {
        return id;
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
