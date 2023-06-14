package dtos;

import entities.Assignment;
import entities.Member;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MemberDTO implements Serializable {
    private final String address;
    private final int phone;
    private final String email;
    private final String birthday;
    private final String account;
    private final List<AssignmentDTO> assignments;

    public MemberDTO(Member m) {
        this.address = m.getAddress();
        this.phone = m.getPhone();
        this.email = m.getEmail();
        this.birthday = m.getBirthday();
        this.account = m.getAccount();
        this.assignments = m.getAssignments().stream().map(a -> new AssignmentDTO(a)).collect(Collectors.toList());
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDTO entity = (MemberDTO) o;
        return Objects.equals(this.address, entity.address) &&
                Objects.equals(this.phone, entity.phone) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.birthday, entity.birthday) &&
                Objects.equals(this.account, entity.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, phone, email, birthday, account);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "address = " + address + ", " +
                "phone = " + phone + ", " +
                "email = " + email + ", " +
                "birthday = " + birthday + ", " +
                "account = " + account + ")";
    }
}
