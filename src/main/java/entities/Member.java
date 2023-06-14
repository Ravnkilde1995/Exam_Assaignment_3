package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String address;
    private int phone;
    private String email;
    private String birthday;
    private String account;
    @ManyToMany
    private List<Assignment> assignments;

    public Member() {
    }

    public Member(String address, int phone, String email, String birthday, String account) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.account = account;
        this.assignments = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}