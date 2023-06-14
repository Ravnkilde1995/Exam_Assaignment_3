package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "assignment")
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
    private List<Member> members;
    @ManyToOne
    private Dinnerevent event;


    public Assignment() {
    }

    public Assignment(String famName, String info) {
        this.famName = famName;
        this.info = info;
        this.date = new Date();
        this.members = new ArrayList<>();
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

    public List<Member> getMembers() {
        return members;
    }

    public void addMembers(Member member) {
        if(member != null){
            this.members.add(member);
            member.getAssignments().add(this);
        }
    }

    public Dinnerevent getEvent() {
        return event;
    }

    public void setEvent(Dinnerevent event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", famName='" + famName + '\'' +
                ", date=" + date +
                ", info='" + info + '\'' +
                ", members=" + members +
                ", event=" + event +
                '}';
    }
}

