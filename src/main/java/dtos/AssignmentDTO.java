package dtos;

import entities.Assignment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AssignmentDTO implements Serializable {
    private final Long id;
    private final String famName;
    private final Date date;
    private final String info;
    private final List<String> members;

    public AssignmentDTO(Assignment a) {
        this.id = a.getId();
        this.famName = a.getFamName();
        this.date = a.getDate();
        this.info = a.getInfo();
        this.members = a.getMembers().stream().map(o -> o.getAccount()).collect(Collectors.toList());

    }

    public static List<AssignmentDTO> getDtos(List<Assignment> assignments){
        List<AssignmentDTO> assignmentDTOS = new ArrayList();
        assignments.forEach(assignment->assignmentDTOS.add(new AssignmentDTO(assignment)));
        return assignmentDTOS;
    }

    public Long getId() {
        return id;
    }

    public String getFamName() {
        return famName;
    }

    public Date getDate() {
        return date;
    }

    public String getInfo() {
        return info;
    }

    public List<String> getMembers() {
        return members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentDTO entity = (AssignmentDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.famName, entity.famName) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.info, entity.info) &&
                Objects.equals(this.members, entity.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, famName, date, info, members);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "famName = " + famName + ", " +
                "date = " + date + ", " +
                "info = " + info + ", " +
                "members = " + members ;
    }
}
