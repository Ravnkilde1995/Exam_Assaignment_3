package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dinnerevent")
public class Dinnerevent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String time;
    private String location;
    private String dish;
    private int price;
    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    private List<Assignment> assignments;

    public Dinnerevent() {
    }

    public Dinnerevent(String time, String location, String dish, int price) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.price = price;
        this.assignments = new ArrayList<>();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignments(Assignment assignment) {
        this.assignments.add(assignment);
        if (assignment != null) {
            assignment.setEvent(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}