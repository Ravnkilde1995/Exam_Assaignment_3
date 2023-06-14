package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "assaignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String famName;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String info;
    @ManyToMany(mappedBy = "assignments", cascade = CascadeType.PERSIST)
    private List<Dinnerevent> events;

    public Assignment() {
    }

    public Assignment(String famName, Date date, String info) {
        this.famName = famName;
        this.date = date;
        //this.date = new Date();
        this.info = info;
        this.events = new ArrayList<>();
    }

    public List<Dinnerevent> getEvent() {
        return events;
    }

    public void addEvent(Dinnerevent event) {
        if (event != null) {
            this.events.add(event);
            event.getAssignments().add(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamName() {
        return famName;
    }

    public void setFamName(String famName) {
        this.famName = famName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}

